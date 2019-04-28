package part01.lesson05;

/**
 *
 * Основной метод
 */
public class Main {
    public static void main(String[] args) throws Exception {

        Person p1 = new Person("Ivan", 17, true);
        Person p2 = new Person("Maris", 20, false);
        Person p3 = new Person("Alex", 34, true);
        Person p4 = new Person("Di", 10, false);

        Pet pet1 = new Pet(2323, "tom", p1, 5);
        Pet pet2 = new Pet(3434, "Bars", p1, 4);
        Pet pet3 = new Pet(1111, "tisha", p2, 15);
        Pet pet4 = new Pet(2222, "tom", p3, 10);
        Pet pet5 = new Pet(2328, "murka", p4, 2);

        Repository rep = new Repository();
        rep.addPerson(p1);
        rep.addPerson(p2);
        rep.addPerson(p3);
        rep.addPerson(p4);

        rep.addPet(pet1);
        rep.addPet(pet2);
        rep.addPet(pet3);
        rep.addPet(pet4);
        rep.addPet(pet5);

        System.out.println(rep.searchByPetName("toma"));
        System.out.println(rep.searchByPetId(2323));

        System.out.println(rep.sort());

        System.out.println("first" + rep.sort());

        rep.changePet(2328, new Pet(2328, "shurka", p4, 3 ));

        System.out.println("second   " + rep.sort());
    }
}
