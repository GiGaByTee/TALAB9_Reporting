Feature: Send Message
  Scenario Outline: Create draft message and send
    When User "<login>" and "<password>" authorise into account
    Then Create draft message
    Then Go to draft message and click on last message
    Then Send message

    Examples:
      | login                 | password       |
      | paprika0020@gmail.com | 423489123789op |
      | paprika0019@gmail.com | 423489123789op |
      | paprika0018@gmail.com | 423489123789op |
      | paprika0017@gmail.com | 423489123789op |
      | paprika0015@gmail.com | 423489123789op |
