# String WebFlux
* [Document](https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html#webflux)

원래 웹 프레임워크인 Spring Web MVC는 Servlet API 및 Servlet 컨테이너용으로 제작되었습니다. 반응형 스택 웹 프레임워크인 Spring WebFlux는 나중에 버전 5.0에 추가되었습니다. 완전 non-blocking이고, 반응형 스트림 back pressure 지원하며, Netty, Undertow 및 Servlet 컨테이너와 같은 서버에서 실행됩니다.
두 웹 프레임워크 모두 소스 모듈(spring-webmvc 및 spring-webflux)의 이름을 미러링하고 Spring 프레임워크에서 나란히 공존하며, 각 모듈은 선택 사항입니다. 애플리케이션은 하나 또는 다른 모듈을 사용할 수 있으며 경우에 따라 둘 다 사용할 수 있습니다. 반응형 WebClient가 있는 Spring MVC 컨트롤러를 예로 들 수 있습니다.

## 목차
* [Overview](#Overview)
* [Define “Reactive”](#Define“Reactive”)
* [Reactive API](#Reactive API)

* * *
## [Overview](https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html#webflux-new-framework)
* * *
왜 만들어졌나?

답의 일부분으로 적은 수의 스레드로 동시성을 처리하고 더 적은 하드웨어 리소스로 확장할 수 있는 non-blocking web 스택의 니즈 때문입니다. 서블릿 non-blocking I/O는 동기방식(Filter, Servlet)이거나 blocking(getParameter, getPart)이 약속된 Servlet rest API와 멀리합니다. 이것이 모든 non-blocking runtime에서 기초 역할을 하는 새로운 common API에 대한 동기였습니다. 이는 비동기 non-blocking 공간에 잘 구축된 서버(Netty와 같은) 때문에 중요합니다.

또 다른 부분의 답은 함수형 프로그래밍입니다. Java 8에 추가된 람다 표현식은 Java에 함수형 API에 대한 기회를 만들었습니다. 이는 비동기 로직의 구성을 허용하는 non-blocking 애플리케이션 및 연속적인 스타일의 API(CompletableFuture 및 ReactiveX로 대중화됨)에 유용합니다. 프로그래밍 모델 레벨에서 Java 8은 Spring WebFlux가 주석이 달린 컨트롤러와 함께 함수형 웹 엔드포인트를 제공할 수 있도록 했습니다.

* * *
### [Define “Reactive”](https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html#webflux-why-reactive)
* * *
우리는 "non-blocking"과 "함수형"을 다루었지만 반응형이란 무엇을 의미할까?

"반응형"이란 용어는 I/O 이벤트에 반응하는 네트워크 구성 요소, 마우스 이벤트에 반응하는 UI 컨트롤러 등 변화에 반응하도록 구축된 프로그래밍 모델을 의미합니다. 그런 의미에서 non-blocking은 반응형입니다. 왜냐하면 차단되는 대신 이제 작업이 완료되거나 데이터를 사용할 수 있게 되면 알림에 반응하는 모드이기 때문입니다. 

Spring 팀에서 "반응형"과 연관시키는 또 다른 중요한 메커니즘이 있는데 그것이 non-blocking back pressure 입니다. 동기식 명령형 코드에서 blocking 호출은 호출자가 대기하도록 강제하는 자연스러운 형태의 back pressure 역할을 합니다. non-blocking 코드에서는 빠른 생성자가 목적지를 매몰하지 않도록 이벤트 속도를 제어하는 것이 중요합니다.

반응형 스트림는 비동기 구성 요소와 back pressure 간의 상호 작용을 정의하는 작은 사양(Java 9에서도 채택됨)입니다. 예를 들어, 데이터 저장소(제공자 역할)는 HTTP 서버(구독자 역할)가 응답에 쓸 수 있는 데이터를 생성할 수 있습니다. 반응형 스트림 주요 목적은 구독자가 제공자가 데이터를 생성하는 속도 또는 속도를 제어할 수 있도록 하는 것입니다.

    일반적인 질문: 게시자가 속도를 늦출 수 없으면 어떻게 됩니까?
    반응형 스트림의 목적은 메커니즘과 경계를 설정하는 것입니다.
    게시자가 속도를 줄일 수 없는 경우 버퍼링, 삭제 또는 실패 여부를 결정해야 합니다.

* * *
### [Reactive API](https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html#webflux-reactive-api)
* * *
반응형 스트림은 상호 운용성에 중요한 역할을 합니다. 라이브러리 및 인프라 구성 요소에 관심이 있지만 너무 low-level이기 때문에 애플리케이션 API로는 유용하지 않습니다. 애플리케이션은 비동기 로직을 구성하기 위해 higher-level의 다양한 기능을 갖춘 API가 필요합니다(Java 8 Stream API와 유사하지만 collenctions 뿐만이 아닌). 이것이 반응형 라이브러리가 하는 역할입니다.

Reactor는 Spring WebFlux를 위한 반응형 라이브러리입니다. 0..1 (Mono) and 0..N (Flux)의 데이터 시퀀스에서 동작하는 Mono 및 Flux API 유형을 제공합니다(연산자의 ReactiveX vocabulary에 할당된 연산자 세트를 통해). Reactor는 반응형 스트림 라이브러리며, 모든 operator가 non-blocking back pressure를 지원합니다. Reactor는 서버 측 Java에 중점을 두고 있고, Spring과 긴밀한 협업으로 개발되었습니다. 

WebFlux에는 Reactor가 핵심 종속성으로 필요하지만 반응형 스트림을 통해 다른 반응형 라이브러리와 상호 운용할 수 있습니다. 일반적으로 WebFlux API는 일반 제공자를 입력으로 수락하고 내부적으로 이를 Reactor 유형에 맞게 조정한 다음 Flux 또는 Mono를 출력으로 반환합니다. 따라서 모든 제공자를 입력으로 전달하고 출력에 작업을 적용할 수 있지만 다른 반응형 라이브러리에서 사용할 수 있도록 출력을 조정해야 합니다. 실행할 때마다(예: 주석이 달린 컨트롤러) WebFlux는 RxJava 또는 다른 반응형 라이브러리의 사용에 투명하게 적응합니다. 자세한 내용은 [Reactive 라이브러리](https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html#webflux-reactive-libraries)를 참조하십시오. 

    Reactive API 외에도,
    WebFlux는 Kotlin의 Coroutines API와도 함께 사용할 수 있으며,
    이는 보다 엄숙한 프로그래밍 스타일을 제공합니다.

* * *
### [Programming Models](https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html#webflux-programming-models)
* * *

Spring-Web 모듈은 Spring WebFlux(HTTP 추상화, 지원되는 서버용 Reactive Streams 어댑터, 코덱 및 Servlet API와 유사하지만 non-blocking contracts가 있는 코어 WebHandler API를 포함하는)의 기반이 되는 reactive foundation를 담고 있습니다.

이를 바탕으로 Spring WebFlux는 두 가지 프로그래밍 모델을 선택할 수 있습니다:

* Annotated Controllers: 스프링 MVC와 일치하며 스프링 웹 모듈의 동일한 Annotation을 기반으로 합니다. Spring MVC와 WebFlux 컨트롤러는 모두 반응형(Reactor 및 RxJava) 리턴 유형을 지원하므로 구별하기가 쉽지 않습니다. 한 가지 주목할 만한 차이점은 WebFlux가 반응형 @RequestBody 인수도 지원한다는 것입니다.
* Functional Endpoints: 람다 기반, 경량 및 기능 프로그래밍 모델. 애플리케이션이 요청을 라우팅하고 처리하는 데 사용할 수 있는 작은 라이브러리 또는 유틸리티 집합이라고 생각할 수 있습니다. Annotated Controllers와의 큰 차이점은 애플리케이션이 처음부터 끝까지 요청 처리를 담당하는 반면 annotation을 통해 의도를 선언하고 다시 호출된다는 것입니다.

* * *
### [Applicability](https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html#webflux-framework-choice)
* * *

Spring MVC or WebFlux?

자연스러운 질문일지만 부적절한 이분법을 생각하도록 하는 질문입니다. 실제로 두 가지 모두 함께 작동하여 사용 가능한 옵션의 범위를 확장합니다. 이 두 가지는 서로의 연속성과 일관성을 위해 설계되었으며, 나란히 사용할 수 있으며, 각 측면의 피드백은 양측 모두에게 도움이 됩니다. 다음 다이어그램은 두 가지가 어떻게 관련되어 있는지, 공통점은 무엇인지, 각각 고유하게 지원하는 것은 무엇인지를 보여줍니다:

![spring-mvc-and-webflux-venn](./img/spring-mvc-and-webflux-venn.png)

다음과 같은 구체적인 사항을 고려하는 것이 좋습니다:

* 정상적으로 작동하는 Spring MVC 애플리케이션이 있으면 변경할 필요가 없습니다. 명령형 프로그래밍은 코드를 작성, 이해 및 디버그하는 가장 쉬운 방법입니다.
* 이미 non-blocking 웹 스택을 구현한 경우, Spring WebFlux는 이 공간의 다른 실행 모델과 동일한 이점을 제공하며 서버(Netty, Tomcat, Jetty, Underow 및 Servlet 컨테이너), 프로그래밍 모델(주석이 있는 컨트롤러 및 기능적 웹 엔드포인트), 반응형 라이브러리(Reactor, RxJava 또는 기타)를 선택할 수 있습니다.
* Java 8 lambdas 또는 Kotlin과 함께 사용할 수 있는 가볍고 기능적인 웹 프레임워크에 관심이 있다면 Spring WebFlux 기능형 웹 엔드포인트를 사용할 수 있습니다. 이는 또한 요구사항이 덜 복잡한 소규모 애플리케이션이나 마이크로 서비스에 적합하며 투명성과 제어 기능을 강화할 수 있습니다.
* 마이크로서비스 아키텍처에서는 Spring MVC 또는 Spring WebFlux 컨트롤러 또는 Spring WebFlux 기능형 엔드포인트와 함께 다양한 애플리케이션을 사용할 수 있습니다. 두 프레임워크 모두에서 동일한 annotation 기반 프로그래밍 모델을 지원하므로 지식을 재사용하는 동시에 올바른 작업에 적합한 도구를 선택하는 것이 더 쉬워집니다.
* 응용프로그램을 평가하는 간단한 방법은 종속성을 확인하는 것입니다. 사용할 차단 지속성 API(JAP,JDBC) 또는 네트워킹 API가 있는 경우 Spring MVC가 공통 아키텍처에 가장 적합합니다. Reactor와 RxJava 모두에서 별도의 스레드에서 blocking calls를 수행하는 것은 기술적으로 가능하지만 non-blocking 웹 스택을 최대한 활용하지는 못할 것입니다.
* 원격 서비스에 대한 호출이 있는 Spring MVC 응용 프로그램이 있는 경우 반응형 WebClient를 사용해 보십시오. Spring MVC 컨트롤러 메소드에서 직접 반응형(Reactor, RxJava 또는 기타)을 반환할 수 있습니다. 호출당 지연 시간이나 호출 간의 상호 의존성이 클수록 더 큰 이점을 얻을 수 있습니다. Spring MVC 컨트롤러는 다른 반응형 구성 요소도 호출할 수 있습니다.
* 대규모 팀이 있는 경우, non-blocking, 기능 및 선언적 프로그래밍으로 전환할 때 가파른 학습 곡선을 염두에 두십시오. 완전한 교체 없이 시작하는 실질적인 방법은 반응형 Web Client를 사용하는 것입니다. 그 외에도 소규모로 시작하여 이점을 측정합니다. 광범위한 애플리케이션의 경우 전환이 필요하지 않을 것으로 예상됩니다. 어떤 이점을 찾아야 할지 모르는 경우 non-blocking I/O의 작동 방식(예: 단일 스레드 Node.js의 동시성)과 그 영향에 대해 알아봅니다.

* * *
### [Servers](https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html#webflux-server-choice)
* * *

Spring WebFlux는 Tomcat, Jetty, Servlet 컨테이너뿐만 아니라 Netty 및 Undertow와 같은 서블릿이 아닌 런타임에서도 지원됩니다. 모든 서버는 low-level의 공통 API에 맞게 조정되어 서버 전체에서 higher-level의 프로그래밍 모델을 지원할 수 있습니다. 

Spring WebFlux에는 서버를 시작하거나 중지할 수 있는 기본 제공 기능이 없습니다. 그러나 Spring 구성 및 WebFlux 인프라에서 응용프로그램을 쉽게 구성하고 몇 줄의 코드로 실행할 수 있습니다.

Spring Boot에는 이러한 단계를 자동화하는 WebFlux 스타터가 있습니다. 기본적으로 스타터는 Netty를 사용하지만 Maven 또는 Gradle 종속성을 변경하여 Tomcat, Jetty 또는 Underow로 쉽게 전환할 수 있습니다. Spring Boot은 비동기 non-blocking에서 더 널리 사용되고 클라이언트와 서버가 리소스를 공유할 수 있기 때문에 Netty로 기본 설정됩니다.

Tomcat 및 Jetty는 Spring MVC 및 WebFlux와 함께 사용할 수 있습니다. 그러나 사용 방법은 매우 다르다는 것을 참조하십시오. Spring MVC는 서블릿 blocking I/O에 의존하며, 필요한 경우 애플리케이션이 서블릿 API를 직접 사용할 수 있습니다. Spring WebFlux는 서블릿 non-blocking I/O를 사용하며 low-level 어댑터 뒤에 서블릿 API를 사용합니다. 직접 사용할 수 있도록 노출되지 않았습니다.

Undertow의 경우, Spring WebFlux는 서블릿 API 없이 Undertow API를 직접 사용합니다.

* * *
### [Performance](https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html#webflux-performance)
* * *

성능에는 많은 특징과 의미가 있습니다. 일반적으로 반응형이고 non-blocking이라고 해서 애플리케이션 실행 속도가 빨라지는 것은 아닙니다. 예를 들어, WebClient를 사용하여 원격 호출을 병렬로 실행하는 경우 등과 같은 경우에 사용할 수 있습니다. 전반적으로, non-blocking 방식으로 작업을 수행하려면 더 많은 작업이 필요하며, 이는 필요한 처리 시간을 약간 증가시킬 수 있습니다. 

반응형 및 non-blocking의 주요 기대 이점은 적은 수의 고정된 수의 스레드와 적은 메모리로 확장할 수 있다는 것입니다. 이는 애플리케이션이 보다 예측 가능한 방식으로 확장되기 때문에 로드 시 애플리케이션의 복원력을 향상시킵니다. 그러나 이러한 이점을 확인하려면 지연 시간이 어느 정도 있어야 합니다(느리고 예측할 수 없는 네트워크 I/O 혼합 포함). 여기서 반응형 스택이 장점을 드러내기 시작하며, 차이는 극적일 수 있습니다.


## WebClient
* [Document](https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html#webflux-client)


