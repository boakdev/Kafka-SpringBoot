package com.boakdev.shopapi.controller;


import com.boakdev.shopapi.dto.ShopDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.boakdev.shopapi.service.ShopService;

import java.util.List;


@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
public class ShopController {

    @Autowired
    private ShopService shopService;

    @GetMapping
    public ResponseEntity<List<ShopDTO>> getShop() {
        List<ShopDTO> shopDTOList = shopService.getShop();
        return ResponseEntity.ok().body(shopDTOList);
    }

    @PostMapping
    public ResponseEntity<ShopDTO> saveShop(@RequestBody ShopDTO shopDTO) {
        shopDTO = shopService.saveShop(shopDTO);
        return ResponseEntity.ok().body(shopDTO);
    }
}
