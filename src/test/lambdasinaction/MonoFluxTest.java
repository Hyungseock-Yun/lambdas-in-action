package lambdasinaction;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class MonoFluxTest {

  @Test @Disabled
  public void mono() {
    Mono<String> stringMono = Mono.just("Welcome to Reactor")
      .map(msg -> msg.concat(".com"))
      .map(String::toUpperCase)
      .log();
    System.out.println("stringMono = " + stringMono);

    stringMono.subscribe(System.out::println);

    StepVerifier.create(stringMono)
      .expectNext("WELCOME TO REACTOR.CO")
      .verifyComplete();
  }

  @Test
  public void verify() {
    Flux<Integer> integerFlux = Flux.range(10, 10)
      .filter(num -> Math.floorMod(num, 2) == 1)
      .log();

    integerFlux.subscribe(System.out::println);

    StepVerifier.create(integerFlux)
        .expectNext(11, 13, 15, 17, 19)
        .verifyComplete();
  }
}
