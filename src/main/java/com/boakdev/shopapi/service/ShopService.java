package com.boakdev.shopapi.service;

import com.boakdev.shopapi.dto.ShopDTO;
import com.boakdev.shopapi.entity.Shop;
import com.boakdev.shopapi.entity.ShopItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.boakdev.shopapi.repository.ShopRepository;


import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;

    @Transactional(readOnly = true)
    public List<ShopDTO> getShop(){
        return shopRepository
                .findAll()
                .stream()
                .map(shop -> ShopDTO.convert(shop))
                .collect(Collectors.toList());
    }

    public ShopDTO saveShop(ShopDTO shopDTO){
        shopDTO.setIdentifier(UUID.randomUUID().toString());
        shopDTO.setDateShop(LocalDate.now());
        shopDTO.setStatus("PENDING");

        Shop shop = Shop.convert(shopDTO);
        for(ShopItem shopItem : shop.getItems()){
            shopItem.setShop(shop);
        }

        return ShopDTO.convert(shopRepository.save(shop));
    }
}
