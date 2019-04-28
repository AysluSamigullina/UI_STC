package part01.lesson05;

import java.util.*;

/**
 * Картотека
 */
public class Repository {

    private ArrayList<Pet> pets;
    private ArrayList<Person> persons;
    private HashMap<String, ArrayList<Pet>> nameMap;
    private HashMap<Integer, Pet> idMap;

    public Repository() {
        pets    = new ArrayList<Pet>();
        nameMap = new HashMap<>();
        idMap   = new HashMap<>();
        persons = new ArrayList<Person>();
    }

    /**
     * Добавляет животное в картотеку
     *
     * @param pet
     * @throws Exception
     */
    public void addPet(Pet pet) throws Exception {
        if (idMap.containsKey(pet.getId())) {
            throw new Exception("повтор ID");
        }

        idMap.put(pet.getId(), pet);

        if (!nameMap.containsKey(pet.getPetname())) {
            nameMap.put(pet.getPetname(), new ArrayList<Pet>());
        }

        nameMap.get(pet.getPetname()).add(pet);
    }

    /**
     * Добавляет хозяина в картотеку
     *
     * @param person
     */

    public void addPerson(Person person) {
        persons.add(person);
    }

    /**
     * Выполняет поиск животного по кличке
     *
     * @param name
     * @return
     */
    public ArrayList<Pet> searchByPetName(String name) {
        return nameMap.get(name);
    }

    /**
     * Выполняет поиск животного по id
     *
     * @param id
     * @return
     */
    public Pet searchByPetId(Integer id) {
        return idMap.get(id);
    }

    /**
     * Выполняет сортировку животных
     * @return
     */
    public ArrayList<Pet> sort() {

        ArrayList<Pet> pets1 = new ArrayList<>(idMap.values());
        pets1.sort(Comparator.comparing(Pet::getPersonName)
                .thenComparing(Pet::getPetname)
                .thenComparing(Pet::getWeight)
        );

        return pets1;
    }

    /**
     * Вносит изменения в картотеку по id
     *
     * @param id
     * @param pet
     */
    public void changePet(int id, Pet pet) {
        String oldName = idMap.get(id).getPetname();

        idMap.put(id, pet);
        String newName = idMap.get(id).getPetname();

        ArrayList<Pet> pets2 = nameMap.get(oldName);
        Iterator<Pet> itr    = pets2.iterator();
        while (itr.hasNext()) {
            if (itr.next().getId() == id && !(oldName.equals(newName))) {
                itr.remove();
            }
        }

        if (!nameMap.containsKey(pet.getPetname())) {
            nameMap.put(pet.getPetname(), new ArrayList<Pet>());
        }

        nameMap.get(pet.getPetname()).add(pet);
    }
}
