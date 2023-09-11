function fn() {
  karate.configure('retry', { count: 3, interval: 5000 });
  karate.configure('connectTimeout', 30000);
  karate.configure('readTimeout', 100000);
  karate.configure('report', {showLog: true, showAllSteps: false});

  var env = karate.env || 'DEV';
  var protocol = 'https';

if(!env)
{
env = 'DEV';
}
       var config = {
       env: env ,
       };
   if (env.toUpperCase() == 'DEV')
   {
         config.BaseUrl= protocol + '://reqres.in/api/users?page=2'
         config.PostUrl= protocol + '://reqres.in/api/users?delay=3'
   }
   else if (env.toUpperCase() == 'INT')
   {
         config.BaseUrl= protocol + '://reqres.in/api/users?page=2'
         config.PostUrl= protocol + '://reqres.in/api/users?delay=3'
   }
   else if (env.toUpperCase() == 'QA')
   {
         config.BaseUrl= protocol + '://reqres.in/api/users?page=2'
         config.PostUrl= protocol + '://reqres.in/api/users?delay=3'
   }
   return config;
 }

