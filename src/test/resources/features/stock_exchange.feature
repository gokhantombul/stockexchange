Feature: Stock Exchange Management

  Background:
    Given The application is running
    And The user is authenticated

  Scenario: Adding a new Stock to a Stock Exchange and verifying it
    Given A stock exchange "Borsa Istanbul" exists with the following properties:
      | id | name           | description              | liveInMarket |
      | 1  | Borsa Istanbul | Istanbul stock exchange  | false        |
    And The following stock exists:
      | id | name     | description            | currentPrice | lastUpdate               |
      | 1  | AKBNK    | Akbank stock           | 100.0        | 2024-08-16 10:00:00      |
    When The user adds the stock "AKBNK" to the stock exchange "Borsa Istanbul"
    Then The stock exchange "Borsa Istanbul" should have 1 stock(s)
    And The "liveInMarket" status of the stock exchange "Borsa Istanbul" should be "false"

  Scenario: Making the Stock Exchange live after adding 5 stocks
    Given A stock exchange "Borsa Istanbul" exists with 4 stocks and "liveInMarket" status "false"
    When The user adds another stock to the stock exchange "Borsa Istanbul"
    Then The "liveInMarket" status of the stock exchange "Borsa Istanbul" should be "true"
