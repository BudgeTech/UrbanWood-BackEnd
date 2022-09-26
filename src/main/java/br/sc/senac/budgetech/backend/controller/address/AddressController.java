package br.sc.senac.budgetech.backend.controller.address;

import br.sc.senac.budgetech.backend.dto.address.AddressDTO;
import br.sc.senac.budgetech.backend.projection.address.AddressProjection;
import br.sc.senac.budgetech.backend.service.address.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/address")
@AllArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    public ResponseEntity<AddressDTO> addAddress(@RequestBody AddressDTO addressDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(addressService.save(addressDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateAddress(@RequestBody AddressDTO addressDTO, @PathVariable(value = "id") Long id) {
        addressService.update(addressDTO, id);
        return ResponseEntity.status(HttpStatus.OK).body("Address updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable(value = "id") Long id) {
        addressService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Address deleted successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressProjection> getProjectionById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(addressService.findById(id));
    }

    @GetMapping("neighborhood/{neighborhood}")
    public ResponseEntity<List<AddressProjection>> getProjectionByNeighborhood(@PathVariable(value = "neighborhood") String neighborhood) {
        return ResponseEntity.status(HttpStatus.OK).body(addressService.findByNeighborhood(neighborhood));
    }
}