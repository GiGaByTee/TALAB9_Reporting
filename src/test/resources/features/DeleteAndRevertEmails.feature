Feature: Management of emails in Gmail

  Scenario Outline: Delete emails and revert the action
    Given User is logged in Gmail with credential "<login>" and "<passw>"
    When User delete emails and clicks revert action button
    Then Emails are reverted and proper message is displayed

    Examples:
      | login     | passw      |
      | tt6549624 | Qwerty123! |
      | tt6549625 | Qwerty123! |
      | tt6549626 | Qwerty123! |
      | tt6549627 | Qwerty123! |
      | tt6549629 | Qwerty123! |