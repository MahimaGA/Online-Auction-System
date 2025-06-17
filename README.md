# Online Auction System (Java EE EJB-Based Application)

## Introduction

In the context of rapidly evolving digital commerce, the need for responsive, scalable, and modular online auction systems has become increasingly critical. This project explores the design and implementation of a distributed online auction system developed using Enterprise JavaBeans (EJB), Contexts and Dependency Injection (CDI), Java Message Service (JMS), and WebSocket technologies. Designed as part of an academic final-year coursework requirement, the application aims to provide a robust and research-grounded solution to the challenges of real-time bidding systems. The proposed system allows users to register, authenticate, join auctions, and place bids in a live environment, where consistent state synchronization and fault-tolerant communication are of utmost importance. The software leverages EAR-based modular deployment to facilitate separation of concerns, scalability, and concurrent processing using container-managed services.

## Problem Domain

Modern auction platforms are tasked with supporting high levels of concurrent user interaction, real-time responsiveness, and data integrity. These requirements are compounded when systems must scale across distributed networks and support real-time synchronization. Existing solutions often lack the architectural modularity required for scalable development, or fail to incorporate asynchronous messaging for concurrent event handling. Traditional systems tend to perform poorly under concurrent access or when broadcasting real-time updates, leading to race conditions, delayed bid propagation, and inconsistent auction states across user interfaces.

This project seeks to address these shortcomings by constructing an online auction system that is designed to handle real-time events across geographically distributed clients. The system not only allows for live bid updates but ensures that concurrent users can interact without corrupting the auction’s state. Special emphasis has been placed on designing a system that promotes modularity and maintainability, aligning with enterprise-grade development standards.

## Research Gap and Objectives

Although numerous auction platforms exist, few employ the complete suite of Java EE technologies to their full extent, particularly in an educational or research context. Many systems overlook advanced patterns such as container-managed concurrency and JMS-based asynchronous communication. The research gap addressed here involves developing a real-time, distributed auction system that not only demonstrates these technologies but also evaluates their effectiveness in ensuring consistency and scalability under concurrent load.

The system’s objectives are to demonstrate the utility of EJB’s component model for business logic separation, utilize JMS and WebSocket integration for real-time communication, and validate the architecture through performance and concurrency testing. These components are designed to offer a comprehensive example of how Java EE technologies can address complex business requirements in distributed systems.

## System Architecture

The architecture of the application follows a layered enterprise model, packaged as an EAR archive, and consists of two main modules: `auction-ejb` and `auction-web`. The EJB module encapsulates all business services, including user authentication, auction lifecycle management, and bid validation. Singleton session beans manage global application state, while stateful session beans handle user-specific data across sessions. Stateless beans encapsulate discrete business logic components that can scale with demand.

The web module serves as the presentation and controller layer, consisting of JSP pages, servlet-based controllers, and WebSocket endpoints for real-time communication. The application utilizes Payara Server’s built-in OpenMQ for JMS messaging, ensuring asynchronous, fault-tolerant message broadcasting. The system’s design aligns with modularity and reusability principles, promoting ease of maintenance and future extensibility.

A component diagram has been included to visualize the interaction between various EJBs, such as `AuctionManagerBean`, `BidManagerBean`, and `UserSessionManagerBean`, all of which collaborate to maintain application state and process user actions. Additionally, a concurrency sequence diagram demonstrates the concurrent bidding process and how locking mechanisms (`@Lock(LockType.WRITE)`) enforce thread-safe access to critical shared resources.

<div align="left">
  <img src="" alt="online auction system"/>
</div>

## Key Features and Technologies

The application includes core functionalities such as user registration, login authentication, real-time auction participation, and bid broadcasting. Real-time updates are facilitated through WebSocket communication, with each auction room associated with its own WebSocket session group. Bid messages are transmitted through JMS topics, consumed by Message-Driven Beans (MDBs), and broadcast to all clients using asynchronous WebSocket messages.

The technology stack includes Java EE (Jakarta EE) with Payara Server 6.2025.4, employing EJB for business logic, JMS (via OpenMQ) for messaging, and standard Java Web APIs for the user interface. All application data is stored in-memory to simulate session and application scope handling, intentionally avoiding external databases to highlight container-managed memory management and state retention.

## Performance Testing

To evaluate the system’s ability to handle concurrent access and maintain real-time responsiveness, extensive testing was conducted using Apache JMeter. The tests simulated multiple users logging in and interacting with the auction room simultaneously. One critical scenario involved simulating 10 concurrent user logins and bid placements, testing the system’s ability to maintain consistent state and broadcast bid updates under load. The average bid processing time remained under 250 milliseconds, and WebSocket synchronization across clients was consistently accurate.

JMeter was configured with thread groups representing concurrent users, and POST requests were issued to the login servlet and auction bid endpoints. Additionally, test plans simulated real-time bidding surges to validate the system’s locking mechanisms and state integrity.

Screenshots of the JMeter test configurations and results, as well as observed performance metrics, are provided in the repository.

## Installation and Deployment

To deploy the system, clone the repository and open it in a Java EE-compatible IDE such as IntelliJ IDEA. Ensure Payara Server is properly configured with support for EAR deployment. The project consists of Maven modules (`auction-ejb` and `auction-web`) bundled into an EAR file for deployment. Once deployed, the application can be accessed at `http://localhost:8080/auction-web`, where users can register, login, and join active auction sessions.

User simulation can be extended using Apache JMeter to evaluate the system under stress conditions. Pre-created test users (e.g., `user0`, `user1`, ..., `user9`) may be used to validate concurrent session handling and message broadcasting.

## Defect Tracking and Fixes

During development, several notable defects were identified and resolved. One significant issue involved the injection of remote interfaces (`@Remote`) into beans, which led to inconsistent state due to serialization. This was resolved by switching to local interfaces (`@Local`), ensuring object references remained within the same JVM.

Another major defect pertained to the incorrect session scoping of the `UserSessionManagerBean`. Initially injected using `@EJB`, the bean was shared across sessions. The issue was resolved by manually creating and storing the stateful session bean within the `HttpSession` scope, thereby preserving per-user isolation.

Additional fixes included correcting WebSocket connection paths, fixing JMS resource lookups for message-driven beans, and synchronizing bid storage and broadcast processes.

## Conclusion

This project presents a robust, research-informed implementation of a distributed online auction platform using modern Java EE technologies. The system demonstrates effective use of modular architecture, container-managed concurrency, and asynchronous communication. The testing and validation processes confirm its scalability and reliability in concurrent user environments. This application serves as a strong academic reference for students and researchers interested in enterprise Java development, real-time systems, and distributed architecture design.

## License

This project is developed as part of final-year academic coursework and is intended for educational and research purposes only.
