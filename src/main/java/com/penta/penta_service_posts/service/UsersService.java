package com.penta.penta_service_posts.service;

import com.penta.penta_service_posts.domain.Users;
import com.penta.penta_service_posts.model.UsersDTO;
import com.penta.penta_service_posts.repos.UsersRepository;
import com.penta.penta_service_posts.util.NotFoundException;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(final UsersRepository usersFTRepository) {
        this.usersRepository = usersFTRepository;
    }

    public List<UsersDTO> findAll() {
        final List<Users> usersFTs = usersRepository.findAll(Sort.by("id"));
        return usersFTs.stream()
                .map(usersFT -> mapToDTO(usersFT, new UsersDTO()))
                .toList();
    }

    public UsersDTO get(final UUID id) {
        return usersRepository.findById(id)
                .map(usersFT -> mapToDTO(usersFT, new UsersDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public UUID create(final UsersDTO usersFTDTO) {
        final Users usersFT = new Users();
        mapToEntity(usersFTDTO, usersFT);
        return usersRepository.save(usersFT).getId();
    }

    public void update(final UUID id, final UsersDTO usersFTDTO) {
        final Users usersFT = usersRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(usersFTDTO, usersFT);
        usersRepository.save(usersFT);
    }

    public void delete(final UUID id) {
        usersRepository.deleteById(id);
    }

    private UsersDTO mapToDTO(final Users usersFT, final UsersDTO usersFTDTO) {
        usersFTDTO.setId(usersFT.getId());
        usersFTDTO.setName(usersFT.getName());
        usersFTDTO.setPicture(usersFT.getPicture());
        return usersFTDTO;
    }

    private Users mapToEntity(final UsersDTO usersFTDTO, final Users usersFT) {
        usersFT.setName(usersFTDTO.getName());
        usersFT.setPicture(usersFTDTO.getPicture());
        return usersFT;
    }

}
