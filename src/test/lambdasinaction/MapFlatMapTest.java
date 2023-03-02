package lambdasinaction;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class MapFlatMapTest {
    /*
        Stream 의 map() 과 flatMap의 차이점 이해
    */
    @Test
    public void transformUsingStream() {
        List<Customer> customers = List.of(
                new Customer(101, "john", "john@gmail.com", Arrays.asList("397937955", "21654725")),
                new Customer(102, "smith", "smith@gmail.com", Arrays.asList("89563865", "2487238947")),
                new Customer(103, "peter", "peter@gmail.com", Arrays.asList("38946328654", "3286487236")),
                new Customer(104, "kely", "kely@gmail.com", Arrays.asList("389246829364", "948609467"))
        );
        //ImmutableCollections이므로 add 하면 java.lang.UnsupportedOperationException 발생
        //customers.add(new Customer(105, "john", "john@gmail.com", Arrays.asList("397937955", "21654725")));

        //email주소 목록 List<String>
        List<String> emailList = customers.stream()  //Stream<Customer>
                .map(cust -> cust.getEmail()) //Stream<String>
                .collect(toList());//List<String>

        emailList.forEach(System.out::println);

        customers.stream()
                .map(Customer::getEmail)
                .collect(toList())
                .forEach(System.out::println);

        //map() : <R> Stream<R> map(Function<? super T,? extends R> mapper)
        List<List<String>> phoneList = customers.stream() //Stream<Customer>
                .map(cust -> cust.getPhoneNumbers()) //Stream<List<String>>
                .collect(toList()); //List<List<String>>
        System.out.println("phoneList = " + phoneList);

        //flatMap : <R> Stream<R> flatMap(Function<? super T,? extends Stream<? extends R>> mapper)
        List<String> phoneList2 = customers.stream() //Stream<Customer>
                .flatMap(customer -> customer.getPhoneNumbers().stream())   //Stream<Stream<List<String>>>
                .collect(toList());
        System.out.println("phoneList2 = " + phoneList2);

        Stream<String> stringStream = customers.stream()
          .flatMap(customer -> customer.getPhoneNumbers().stream());

    }

    static class Customer {
        private int id;
        private String name;
        private String email;
        private List<String> phoneNumbers;

        public Customer(int id, String name, String email, List<String> phoneNumbers) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.phoneNumbers = phoneNumbers;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public List<String> getPhoneNumbers() {
            return phoneNumbers;
        }
    }
}
