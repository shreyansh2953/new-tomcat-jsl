
import mytrackfone.devops.core.*
import mytrackfone.devops.plan_apply.*

def call()
{
   try{

       node{

            def mytool = tool 'Terraform' , type: 'terraform'
              if("${my_choice}"=="apply")
              {
                    stage('cleanup')
                   {
                    def clean = new Mycleanup()
                   clean.cleanup()
                   }
                 stage('checkout')
                  {
                  def ch = new MyCheckout()
                  ch.git_Checkout(url,branch)
                  }
                  stage('plan')
                  {
                    def my_plan = new Plan()
                    my_plan.plan(mytool,var_file,my_region)
                  }
                  stage('apply')
                   {
                     def my_apply = new Apply()
                     my_apply.apply(mytool,var_file,my_region)

                   }
                 stage('output')
                  {
                     def my_outputs = new Output()
                     my_outputs.output(mytool,my_region)
                     println my_outputs
                  }

              }
              else{
                  stage('destroy')
                  {
                  def my_destroy = new Destroy()
                  my_destroy.destroy(mytool,var_file,my_region)

                  }
              }
       }


   }
   catch(e)
   {
         println e
   }




}