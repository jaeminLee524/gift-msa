# gift

- 해당 프로젝트는 Loosed Coupling, High Cohesion을 갖기 위해 DDD의 원칙을 적용하였습니다.

- Domain
  - Entity, Service, Command, Criteria, Factory...

- Layered Architecture
  - User Interfaces, Application, Domain, Infrastructure 영역으로 분리
  - 코드 가독성을 높이고 유지보수성을 높이고자 레이어를 분리
  - User Interfaces: 사용자의 요청을 하위 레이어에 전달하는 역할 
  - Application: 복잡한 비즈니스 로직을 처리
  - Domain: 도메인에 대한 정보, 객체의 상태, 도메인의 비즈니스 로직을 제공
  - Infrastructure: 영속성 구현, 외부와 통신하는 기능 제공

- 주문, 선물하기 프로젝트 중 주문 프로젝트에 해당합니다
  - 선물하기: https://github.com/jaeminLee524/gift-msa
