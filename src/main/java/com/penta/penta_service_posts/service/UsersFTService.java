package com.penta.penta_service_posts.service;

import com.penta.penta_service_posts.domain.UsersFT;
import com.penta.penta_service_posts.model.UsersFTDTO;
import com.penta.penta_service_posts.repos.UsersFTRepository;
import com.penta.penta_service_posts.util.NotFoundException;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class UsersFTService {

    private final UsersFTRepository usersFTRepository;

    public UsersFTService(final UsersFTRepository usersFTRepository) {
        this.usersFTRepository = usersFTRepository;
    }

    public List<UsersFTDTO> findAll() {
        final List<UsersFT> usersFTs = usersFTRepository.findAll(Sort.by("id"));
        return usersFTs.stream()
                .map(usersFT -> mapToDTO(usersFT, new UsersFTDTO()))
                .toList();
    }

    public UsersFTDTO get(final UUID id) {
        return usersFTRepository.findById(id)
                .map(usersFT -> mapToDTO(usersFT, new UsersFTDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public UUID create(final UsersFTDTO usersFTDTO) {
        final UsersFT usersFT = new UsersFT();
        mapToEntity(usersFTDTO, usersFT);
        return usersFTRepository.save(usersFT).getId();
    }

    public void update(final UUID id, final UsersFTDTO usersFTDTO) {
        final UsersFT usersFT = usersFTRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(usersFTDTO, usersFT);
        usersFTRepository.save(usersFT);
    }

    public void delete(final UUID id) {
        usersFTRepository.deleteById(id);
    }

    private UsersFTDTO mapToDTO(final UsersFT usersFT, final UsersFTDTO usersFTDTO) {
        usersFTDTO.setId(usersFT.getId());
        usersFTDTO.setName(usersFT.getName());
        usersFTDTO.setPicture(usersFT.getPicture());
        return usersFTDTO;
    }

    private UsersFT mapToEntity(final UsersFTDTO usersFTDTO, final UsersFT usersFT) {
        usersFT.setName(usersFTDTO.getName());
        usersFT.setPicture(usersFTDTO.getPicture());
        return usersFT;
    }

}
