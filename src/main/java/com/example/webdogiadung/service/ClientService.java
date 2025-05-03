package com.example.webdogiadung.service;

import com.example.webdogiadung.dto.request.ClientRequest;
import com.example.webdogiadung.dto.request.search.ClientSearchRequest;
import com.example.webdogiadung.dto.response.CategoryResponse;
import com.example.webdogiadung.dto.response.ClientResponse;
import com.example.webdogiadung.dto.response.page.PageableData;
import com.example.webdogiadung.dto.response.page.PagingResponse;
import com.example.webdogiadung.entity.CategoryEntity;
import com.example.webdogiadung.entity.ClientEntity;
import com.example.webdogiadung.exception.BusinessException;
import com.example.webdogiadung.exception.DataNotFoundException;
import com.example.webdogiadung.mapper.ClientMapper;
import com.example.webdogiadung.repository.ClientRepository;
import com.example.webdogiadung.service.interfa.ClientServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService implements ClientServiceInterface {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public ClientResponse create(ClientRequest data) {
        ClientEntity clientEntity=clientRepository.findByNameAndEmailAndPhone(data.getName(), data.getEmail(), data.getPhone()).orElse(null);
        if (clientEntity!=null) {
            return clientMapper.toResponse(clientEntity);
        }
        clientEntity = clientMapper.toEntity(data);
        return clientMapper.toResponse(clientRepository.save(clientEntity));
    }

    @Override
    public PagingResponse<ClientResponse> getAll(ClientSearchRequest request) {
        Page<ClientEntity> clientEntityPage=clientRepository.findAll(request.specification(),request.getPaging().pageable());
        return PagingResponse.<ClientResponse>builder()
                .contents(clientMapper.toResponse(clientEntityPage.getContent()))
                .paging(new PageableData()
                        .setPageNumber(clientEntityPage.getNumber())
                        .setPageSize(clientEntityPage.getSize())
                        .setTotalPage(clientEntityPage.getTotalPages())
                        .setTotalRecord(clientEntityPage.getTotalElements()))
                .build();
    }

    @Override
    public ClientResponse getById(String id) {
        return clientMapper.toResponse(clientRepository.findById(id).orElse(null));
    }

    @Override
    public String deleteById(String id, boolean isDelete) {
        if (isDelete) {
            clientRepository.deleteById(id);
        } else {
            clientRepository.findById(id).ifPresent(clientEntity -> {
                clientRepository.save(clientEntity);
            });
        }
        return "Xóa thành công.";
    }

    @Override
    public ClientResponse update(ClientRequest data) {
        var clientEntity = clientRepository.findById(data.getId())
                .orElseThrow(() -> new DataNotFoundException("Sản phẩm không tồn tại."));

        if (clientRepository.findByNameAndEmailAndPhone(data.getName(), data.getEmail(), data.getPhone()).isPresent()) {
            throw new BusinessException("tên mail phone đã tồn tại.");
        }

        clientMapper.updateEntity(clientEntity, data);
        return clientMapper.toResponse(clientRepository.save(clientEntity));
    }

    @Override
    public String deleteByListId(List<String> listId, boolean isDelete) {
        return "";
    }
}
