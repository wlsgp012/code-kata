### Design Pattern
- doc : http://mishadoff.com/blog/clojure-design-patterns

#### Summary
1. Command : 요구나 명령을 형태로 만들어서 클래스로 표현 -> function
2. Strategy : 동일 계열의 알고리즘군을 정의하고, 각각의 알고리즘을 캡슐화 하여 이들의 교환이 가능하도록 만드는 패턴 -> function, which accepts function
3. State : 객체의 내부 상태에 따라 스스로 행동을 변경할 수 있게 허가하는 패턴 -> strategy, depends on state
4. Visitor : 객체 구조를 이루는 원소에 대해 수행할 연산을 표현하는 패턴, 연산을 적용할 원소의 클래스를 변경하지 않고도 새로운 연산을 정의할 수 있게함 -> multiple dispatch
5. Template Method : 객체의 연산에는 알고리즘의 뼈대만, 구체적 처리는 서브클래스쪽으로 미루는 패턴 -> strategy with defaults
6. Iterator : 내부 표현부를 노출하지 않고 어떤 객체 집합에 속한 원소들을 순차적으로 접근할 수 있는 방법 제공 -> sequence
7. Memento : 캡슐화를 위배하지 않은 채로 어떤 객체의 내부 상태를 실체화시켜, 해당 객체가 그 상태로 되돌아올 수 있는 패턴 -> save and restore
8. Prototype : 모형이 되는 인스턴스를 복사해서 인스턴스를 만드는 패턴 -> immutability
9. Mediator : 복수의 클래스가 상호간에 직접 의사 소통을 하지 않고, 중개역을 하는 클래스하고만 의사 소통 -> reduce coupling
10. Observer : 상태가 변화하는 클래스와 그 변화를 통지받는 클래스를 분리 -> function, which calls after another function
11. Interpreter : 문법규칙을 클래스로 표현 -> set of functions to process a tree
12. Flyweight : 크기가 작은 객체가 여러 개 있을 때, 공유를 통해 이들을 효율적으로 사용 -> cache
13. Builder : 객체의 생성과정과 표현방법을 분리하여 동일한 생성 절차에서 다른 표현 결과를 만들 수 있게 하는 패턴 -> optional arguments
14. Facade : 하나의 통합된 인터페이스를 제공하는 패턴 -> single point of access
15. Singleton : 인스턴스는 오직 하나를 보장, 접근할 수 있는 전역적인 포인트를 제공 -> global variable
16. Chain of Responsiblity : 요청을 받을수 있는 객체를 연쇄적으로 묶음, 요청을 보내는 객체와 받는 객체 사이의 결합을 피함 -> function composition
17. Composite : 객체들의 관계를 트리 구조로 구성, 부분-전체 계층을 표현하는 패턴(단일, 복합 개체를 동일하게 핸들링) -> tree
18. Factory Method : 객체를 생성하는 인터페이스는 미리정의, 인스턴스를 만들 클래스의 결정은 서브클래스에서 수행 -> strategy for creating objects
19. Abastract Factory : 공장과 같이 부품을 조합해서 인스턴스 생성을 실행 -> strategy for creating set of related objects
20. Adapter : 특정 인터페이스를 사용자가 기대한는 다른 인터페이스로 변환하는 패턴 -> wrapper, same functionality, different type
21. Decorator : 주어진 상황 및 용도에 따라 어떤 객체에 책임을 덧붙이는 패턴, 기능확장이 필요할때 서브클래싱 대신 쓸수 있는 유연한 대안 -> wrapper, same type, new functionality
22. Proxy : 어떤 객체로 접근을 통제하기 위해, 그 객체의 대리자를 제공하는 패턴 -> wrapper, function composition
23. Bridge : 구현부에서 추상층을 분리하여 각자 독립적으로 변형할 수 있게 하는패턴 -> separate abstraction and implementation
