package controller;


import dto.ShopDTO;
import entity.Shop;
import entity.ShopItem;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import repository.ShopRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
public class ShopController {

    /*
    má prática chamar repository direto do controller,
    somente para agilizar testes, depois implememtar camda service em projeto
     */

    private final ShopRepository shopRepository;

    @GetMapping
    public List<ShopDTO> getShop(){
        return shopRepository
                .findAll()
                .stream()
                .map(shop -> ShopDTO.convert(shop))
                .collect(Collectors.toList());
    }

    @PostMapping
    public ShopDTO saveShop(@RequestBody ShopDTO shopDTO){
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
