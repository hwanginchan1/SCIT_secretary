package net.softsociety.secretary.service;

import java.util.List;

import net.softsociety.secretary.domain.Fridge;

public interface FridgeService {
    List<Fridge> getAllFridges(int familyId);
    List<Integer> addFridge(Fridge fridge);
    void updateFridge(Fridge fridge);
    void deleteFridge(int id);
}
