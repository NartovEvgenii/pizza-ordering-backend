package com.pizza.controller;

import com.pizza.dto.PizzaDto;
import com.pizza.service.PizzaService;
import com.pizza.service.impl.PizzaServiceImpl;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/pizza")
public class PizzaController {
    @Autowired
    private PizzaService pizzaService;
    @Autowired
    private ResourceLoader resourceLoader;

    @GetMapping
    public List<PizzaDto> getAllPizzas() {
        return pizzaService.getAllPizzas();
    }

    @GetMapping(
            value = "/img/{imgName}",
            produces = MediaType.IMAGE_PNG_VALUE
    )
    public @ResponseBody byte[] getImageWithMediaType(@PathVariable String imgName) throws IOException {
        InputStream in = resourceLoader.getResource("classpath:/images/" + imgName).getInputStream();
        return IOUtils.toByteArray(in);
    }
}
