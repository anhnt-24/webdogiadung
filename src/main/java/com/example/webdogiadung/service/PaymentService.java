package com.example.webdogiadung.service;

import com.example.webdogiadung.config.VnpayConfiguration;
import com.example.webdogiadung.constants.OrderStatus;
import com.example.webdogiadung.dto.request.OrderRequest;
import com.example.webdogiadung.exception.BusinessException;
import com.example.webdogiadung.repository.psql.OrderRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.example.webdogiadung.config.VnpayConfiguration.hmacSHA512;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final OrderRepository orderRepository;

   public String createPayment(OrderRequest orderRequest, HttpServletRequest req) throws UnsupportedEncodingException {
       String vnp_Version = "2.1.0";
       String vnp_Command = "pay";
       String orderType = "other";
       long amount = orderRequest.getTotalAmount().longValue() * 100;
//        Integer.parseInt(req.getParameter("amount"))*100
//        String bankCode = req.getParameter("bankCode");

       String vnp_TxnRef = VnpayConfiguration.getRandomNumber(8);
       String vnp_IpAddr = VnpayConfiguration.getIpAddress(req);

       String vnp_TmnCode = VnpayConfiguration.vnp_TmnCode;

       Map<String, String> vnp_Params = new HashMap<>();
       vnp_Params.put("vnp_Version", vnp_Version);
       vnp_Params.put("vnp_Command", vnp_Command);
       vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
       vnp_Params.put("vnp_Amount", String.valueOf(amount));
       vnp_Params.put("vnp_CurrCode", "VND");
       vnp_Params.put("vnp_BankCode", "NCB");
       vnp_Params.put("vnp_TxnRef", orderRequest.getId());


//        if (bankCode != null && !bankCode.isEmpty()) {
//            vnp_Params.put("vnp_BankCode", bankCode);
//        }
//       vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
       vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
       vnp_Params.put("vnp_OrderType", orderType);

//        String locate = req.getParameter("language");
//        if (locate != null && !locate.isEmpty()) {
//            vnp_Params.put("vnp_Locale", locate);
//        } else {
//            vnp_Params.put("vnp_Locale", "vn");
//        }
       vnp_Params.put("vnp_Locale", "vn");
       vnp_Params.put("vnp_ReturnUrl", VnpayConfiguration.vnp_ReturnUrl);
       vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

       Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
       SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
       String vnp_CreateDate = formatter.format(cld.getTime());
       vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

       cld.add(Calendar.MINUTE, 15);
       String vnp_ExpireDate = formatter.format(cld.getTime());
       vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

       List fieldNames = new ArrayList(vnp_Params.keySet());
       Collections.sort(fieldNames);
       StringBuilder hashData = new StringBuilder();
       StringBuilder query = new StringBuilder();
       Iterator itr = fieldNames.iterator();
       while (itr.hasNext()) {
           String fieldName = (String) itr.next();
           String fieldValue = (String) vnp_Params.get(fieldName);
           if ((fieldValue != null) && (fieldValue.length() > 0)) {
               hashData.append(fieldName);
               hashData.append('=');
               hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.UTF_8.toString()));
               query.append(URLEncoder.encode(fieldName, StandardCharsets.UTF_8.toString()));
               query.append('=');
               query.append(URLEncoder.encode(fieldValue, StandardCharsets.UTF_8.toString()));
               if (itr.hasNext()) {
                   query.append('&');
                   hashData.append('&');
               }
           }
       }
       String queryUrl = query.toString();
       String vnp_SecureHash = VnpayConfiguration.hmacSHA512(VnpayConfiguration.secretKey, hashData.toString());
       queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
       String paymentUrl = VnpayConfiguration.vnp_PayUrl + "?" + queryUrl;
       return paymentUrl;
   }
    public String checkPayment(HttpServletRequest request) {
        Map<String, String> fields = new HashMap<>();
        for (Enumeration<String> params = request.getParameterNames(); params.hasMoreElements(); ) {
            String param = params.nextElement();
            String value = request.getParameter(param);
            if (param.startsWith("vnp_")) {
                fields.put(param, value);
            }
        }
        String responseCode = fields.get("vnp_ResponseCode");
        if (!"00".equals(responseCode)) {
            throw new BusinessException("Thanh toán không thành công. Mã lỗi từ VNPAY: " + responseCode);
        }


        String receivedHash = fields.remove("vnp_SecureHash");

        List<String> fieldNames = new ArrayList<>(fields.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        for (int i = 0; i < fieldNames.size(); i++) {
            String key = fieldNames.get(i);
            String value = fields.get(key);
            hashData.append(URLEncoder.encode(key, StandardCharsets.UTF_8));
            hashData.append('=');
            hashData.append(URLEncoder.encode(value, StandardCharsets.UTF_8));
            if (i < fieldNames.size() - 1) {
                hashData.append('&');
            }
        }

        String myHash = hmacSHA512(VnpayConfiguration.secretKey, hashData.toString());

        if (!myHash.equals(receivedHash)) {
            throw new BusinessException("Thanh toán không thành công. Mã hash không khớp.");
        }

        String orderId = fields.get("vnp_TxnRef");
        orderRepository.findById(orderId).ifPresentOrElse(order -> {
            order.setStatus(OrderStatus.PAID);
            orderRepository.save(order);
        }, () -> {
            throw new BusinessException("Không tìm thấy đơn hàng với ID: " + orderId);
        });

        return "Thanh toán thành công.";
    }

}
