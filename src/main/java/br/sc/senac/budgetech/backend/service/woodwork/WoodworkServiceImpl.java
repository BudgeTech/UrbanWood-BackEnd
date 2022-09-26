package br.sc.senac.budgetech.backend.service.woodwork;

import br.sc.senac.budgetech.backend.dto.woodwork.WoodworkDTO;
import br.sc.senac.budgetech.backend.exception.address.AddressNotFoundException;
import br.sc.senac.budgetech.backend.exception.client.ClientLoginRegisteredException;
import br.sc.senac.budgetech.backend.exception.contact.ContactNotFoundException;
import br.sc.senac.budgetech.backend.exception.woodwork.WoodworkCnpjInvalidException;
import br.sc.senac.budgetech.backend.exception.woodwork.WoodworkCnpjRegisteredException;
import br.sc.senac.budgetech.backend.exception.woodwork.WoodworkLoginRegisteredException;
import br.sc.senac.budgetech.backend.exception.woodwork.WoodworkNotFoundException;
import br.sc.senac.budgetech.backend.mapper.woodwork.WoodworkMapper;
import br.sc.senac.budgetech.backend.model.address.Address;
import br.sc.senac.budgetech.backend.model.contact.Contact;
import br.sc.senac.budgetech.backend.model.woodwork.Woodwork;
import br.sc.senac.budgetech.backend.projection.woodwork.WoodworkFulProjection21;
import br.sc.senac.budgetech.backend.projection.woodwork.WoodworkProfileProjectionC8AndC10;
import br.sc.senac.budgetech.backend.projection.woodwork.WoodworkProjection;
import br.sc.senac.budgetech.backend.projection.woodwork.WoodworkSearchProjectionC9;
import br.sc.senac.budgetech.backend.repository.address.AddressRepository;
import br.sc.senac.budgetech.backend.repository.contact.ContactRepository;
import br.sc.senac.budgetech.backend.repository.woodwork.WoodworkRepository;
import br.sc.senac.budgetech.backend.util.CNPJValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class WoodworkServiceImpl implements WoodworkService {

    private final WoodworkRepository woodworkRepository;
    private final WoodworkMapper woodworkMapper;
    private final AddressRepository addressRepository;
    private final ContactRepository contactRepository;

    public WoodworkDTO save(WoodworkDTO woodworkDTO) {

        if (woodworkRepository.existsByCnpj(woodworkDTO.cnpj()))
            throw new WoodworkCnpjRegisteredException("Cnpj " + woodworkDTO.cnpj() + " is already registered");

        if (woodworkRepository.existsByLogin(woodworkDTO.login()))
            throw new ClientLoginRegisteredException("Login " + woodworkDTO.login() + " is already registered");

        if (CNPJValidator.isCNPJ(woodworkDTO.cnpj()))
            throw new WoodworkCnpjInvalidException("Cnpj " + woodworkDTO.cnpj() + " is invalid");

        Contact contact = contactRepository.findById(woodworkDTO.idContact())
                .orElseThrow(() -> new ContactNotFoundException("Contact " + woodworkDTO.idContact() + " was not found"));

        Address address = addressRepository.findById(woodworkDTO.idAddress())
                .orElseThrow(() -> new AddressNotFoundException("Address " + woodworkDTO.idAddress() + " was not found"));

        Woodwork woodwork = woodworkMapper.toEntity(woodworkDTO);
        woodwork.setContact(contact);
        woodwork.setAddress(address);
        Woodwork woodworkSaved = woodworkRepository.save(woodwork);
        return woodworkMapper.toDTO(woodworkSaved);
    }

    public void update(WoodworkDTO woodworkDTO, Long id) {

        Woodwork woodwork = woodworkRepository.findById(id)
                .orElseThrow(() -> new WoodworkNotFoundException("Woodwork " + id + " was not found"));

        Contact contact = contactRepository.findById(woodworkDTO.idContact())
                .orElseThrow(() -> new ContactNotFoundException("Contact " + woodworkDTO.idContact() + " was not found"));

        Address address = addressRepository.findById(woodworkDTO.idAddress())
                .orElseThrow(() -> new AddressNotFoundException("Address " + woodworkDTO.idAddress() + " was not found"));

        if (woodworkRepository.existsByCnpj(woodworkDTO.cnpj()))
            throw new WoodworkCnpjRegisteredException("Cnpj " + woodworkDTO.cnpj() + " is already registered");

        woodwork.setCompanyName(woodworkDTO.companyName());
        woodwork.setDescription(woodworkDTO.description());
        woodwork.setCnpj(woodworkDTO.cnpj());
        woodwork.setLogin(woodworkDTO.login());
        woodwork.setPassword(woodworkDTO.password());
        woodwork.setImage(woodworkDTO.image());
        woodwork.setContact(contact);
        woodwork.setAddress(address);

        Optional<WoodworkProjection> existsCnpj = woodworkRepository.findWoodworkByCnpj(woodworkDTO.cnpj());
        Optional<WoodworkProjection> existsLogin = woodworkRepository.findWoodworkByLogin(woodworkDTO.login());

        if (existsCnpj.isPresent() && (existsCnpj.get().getId().equals(id)))
            throw new WoodworkCnpjRegisteredException("Cnpj " + woodworkDTO.cnpj() + " is already registered");

        if (existsLogin.isPresent() && (existsLogin.get().getId().equals(id)))
            throw new WoodworkLoginRegisteredException("Login " + woodworkDTO.login() + " is already registered");

        if (woodworkDTO.cnpj() != null && CNPJValidator.isCNPJ(woodworkDTO.cnpj()))
            throw new WoodworkCnpjInvalidException("Cnpj " + woodworkDTO.cnpj() + " is invalid");

        woodworkRepository.save(woodwork);
    }

    public void delete(Long id) {
        if (!woodworkRepository.existsById(id))
            throw new WoodworkNotFoundException("Woodwork " + id + " was not found");
        woodworkRepository.deleteById(id);
    }

    public WoodworkProjection findById(Long id) {
        return woodworkRepository.findWoodworkById(id)
                .orElseThrow(() -> new WoodworkNotFoundException("Woodwork " + id + " was not found"));
    }

    public WoodworkProjection findByCnpj(String cnpj) {
        return woodworkRepository.findWoodworkByCnpj(cnpj)
                .orElseThrow(() -> new WoodworkNotFoundException("Woodwork " + cnpj + " was not found"));
    }

    public List<WoodworkProjection> findByAddressNeighborhood(String neighborhood) {
        List<WoodworkProjection> woodwork = woodworkRepository.findWoodworkByAddressNeighborhood(neighborhood);
        if(woodwork.isEmpty())
            throw new WoodworkNotFoundException("Woodwork " + neighborhood + " was not found");
        return woodwork;
    }

    public WoodworkProjection findByContactPhoneNumber(String phoneNumber) {
        return woodworkRepository.findWoodworkByContactPhoneNumber(phoneNumber)
                .orElseThrow(() -> new WoodworkNotFoundException("Woodwork " + phoneNumber + " was not found"));
    }

    public List<WoodworkProjection> findByCompanyName(String companyName) {
        List<WoodworkProjection> woodwork = woodworkRepository.findWoodworkByCompanyName(companyName);
        if(woodwork.isEmpty())
            throw new WoodworkNotFoundException("Woodwork " + companyName + " was not found");
        return woodwork;
    }

    public List<WoodworkSearchProjectionC9> findSearchBy() {
        List<WoodworkSearchProjectionC9> woodwork = woodworkRepository.findWoodworkSearchBy();
        if (woodwork.isEmpty())
            throw new WoodworkNotFoundException("Woodwork was not found");
        return woodwork;
    }

    public WoodworkProfileProjectionC8AndC10 findProfileBy() {
        return woodworkRepository.findWoodworkProfileBy()
                .orElseThrow(() -> new WoodworkNotFoundException("Woodwork was not found"));
    }

    public WoodworkFulProjection21 findProjection21By() {
        return woodworkRepository.findWoodworkFull21By()
                .orElseThrow(() -> new WoodworkNotFoundException("Woodwork was not found"));
    }
}