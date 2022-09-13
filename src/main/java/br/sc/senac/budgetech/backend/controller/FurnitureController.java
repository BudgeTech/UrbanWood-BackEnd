package br.sc.senac.budgetech.backend.controller;

import br.sc.senac.budgetech.backend.dto.furniture.FurnitureDTO;
import br.sc.senac.budgetech.backend.dto.furniture.FurnitureListDTO;
import br.sc.senac.budgetech.backend.dto.furniture.FurnitureScreenDTO;
import br.sc.senac.budgetech.backend.projection.furniture.FurnitureListProjection;
import br.sc.senac.budgetech.backend.projection.furniture.FurnitureProjection;
import br.sc.senac.budgetech.backend.service.furniture.FurnitureService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/furniture")
@AllArgsConstructor
public class FurnitureController {

    private final FurnitureService furnitureService;

    @PostMapping
    public ResponseEntity<FurnitureDTO> addFurniture(@RequestBody FurnitureDTO furnitureDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(furnitureService.save(furnitureDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateFurniture(@RequestBody FurnitureDTO furnitureDTO, @PathVariable(value = "id") Long id) {
        furnitureService.update(furnitureDTO, id);
        return ResponseEntity.status(HttpStatus.OK).body("Furniture updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFurniture(@PathVariable(value = "id") Long id) {
        furnitureService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Furniture deleted successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<FurnitureProjection> getProjectionById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(furnitureService.findById(id));
    }

    @GetMapping("name/{nameFurniture}")
    public ResponseEntity<FurnitureProjection> getProjectionByName(@PathVariable(value = "nameFurniture") String nameFurniture) {
        return ResponseEntity.status(HttpStatus.OK).body(furnitureService.findByNameFurniture(nameFurniture));
    }

    @GetMapping("price/{priceFurniture}")
    public ResponseEntity<FurnitureProjection> getProjectionByPrice(@PathVariable(value = "priceFurniture") double priceFurniture) {
        return ResponseEntity.status(HttpStatus.OK).body(furnitureService.findByPriceFurniture(priceFurniture));
    }

    @GetMapping("furnitureDTO/{id}")
    public ResponseEntity<FurnitureScreenDTO> getProjectionByIDTO(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(furnitureService.findByIdDTO(id));
    }

    @GetMapping("furnitureListDTO/{id}")
    public ResponseEntity<FurnitureListDTO> getProjectionDTOById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(furnitureService.findFurnitureListById(id));
    }

    @GetMapping("page/{page}")
    public ResponseEntity<Page<FurnitureListProjection>> getProjectionPageOrderByName(@PathVariable(value = "page") Integer page) {
        return ResponseEntity.status(HttpStatus.OK).body(furnitureService.findFurnitureOrderByAscName(page));
    }

    @GetMapping("page1/{page}")
    public ResponseEntity<FurnitureListDTO> getFurnitureDTOOrderByNameAsc(@PathVariable(value = "page") Integer page) {
        return ResponseEntity.status(HttpStatus.OK).body(furnitureService.findFurnitureDTOOrderByAscName(page));
    }
}