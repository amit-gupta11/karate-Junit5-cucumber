Feature: Demo
    Background:
      * def jsonModel = read('classpath:base/update.json')
    @setup
    Scenario:
      * def csv = read('classpath:test_data/api/'+env+'/scenario.csv')

      Scenario Outline: <Scenario Description>
        Given url BaseUrl
        When method GET
        Then status 200
        Given url PostUrl
        And set jsonModel.name = '<Name>'
        And set jsonModel.job = '<Job>'
        And print jsonModel
        When method POST
        Then status 201
        And print 'Response is :- ' , response

        Examples:
          | karate.setup().csv |