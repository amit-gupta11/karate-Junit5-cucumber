Feature: Demo
#    Background:
#      * def jsonModel = read('classpath:base/update.json')
    @setup
    Scenario:
      * def csv = read('classpath:test_data/api/'+env+'/scenario.csv')

      Scenario Outline: <Scenario Description>
        Given url BaseUrl+'<Organization>'
        When method GET
        Then status 200
        And print 'Response is :- ' , response
        Then match response.portalConfig.name == '<Organization>'

        Examples:
          | karate.setup().csv |