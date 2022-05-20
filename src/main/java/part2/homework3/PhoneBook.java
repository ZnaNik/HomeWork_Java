package part2.homework3;

import java.util.*;

public class PhoneBook {
    //Телефон это уникальное,
    private HashMap<String,String> innerBook= new HashMap<>();

    public void add(String name, String phone){
        innerBook.put(phone , name.toLowerCase(Locale.ROOT));
    }

    public ArrayList<String> get(String name){
           //Телефоны должны быть уникальными под одним телефоном две фамилии быть не может, и тут приходится искать
        //По значению поскольку поиск происходит по фамилии
        ArrayList<String> retValue = new ArrayList<>();
        innerBook.forEach((k,v) -> {
            if(v.equals(name.toLowerCase()))
                retValue.add(k);
        });

        return retValue;
    }
}
