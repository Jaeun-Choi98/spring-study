package juchoi.study.practice.controller;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import juchoi.study.practice.controller.model.Coffee;

@RestController
@RequestMapping("/coffee")
public class CrudController {
  private List<Coffee> coffees = new ArrayList<>();

  public CrudController() {
    coffees.addAll(List.of(
        new Coffee("coffee A"),
        new Coffee("coffee B"),
        new Coffee("coffee C"),
        new Coffee("coffee D")));
  }

  @GetMapping
  Iterable<Coffee> getCoffee() {
    return this.coffees;
  }

  @GetMapping("/{id}")
  Optional<Coffee> getCoffeeById(@PathVariable String id) {
    for (Coffee coffee : this.coffees) {
      if (coffee.getId().equals(id)) {
        return Optional.of(coffee);
      }
    }
    return Optional.empty();
  }

  @PostMapping
  Coffee postCoffee(@RequestBody Coffee coffee) {
    this.coffees.add(coffee);
    return coffee;
  }

  @PatchMapping
  ResponseEntity<Coffee> putCoffee(@PathVariable String id,
      @RequestBody Coffee coffee) {
    int idx = -1;
    for (Coffee c : this.coffees) {
      if (c.getId().equals(id)) {
        idx = this.coffees.indexOf(c);
        this.coffees.set(idx, coffee);
      }
    }
    return (idx == -1) ? new ResponseEntity<>(this.postCoffee(coffee), HttpStatus.CREATED)
        : new ResponseEntity<>(HttpStatus.OK);
  }

  @DeleteMapping
  void deleteCoffee(@PathVariable String id) {
    this.coffees.removeIf((coffee) -> coffee.getId().equals(id));
  }
}
