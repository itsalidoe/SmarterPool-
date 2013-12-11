package controllers

import play.api.mvc._
import controllers.bootstrap.{Features, Marketing, Carousel, Featurette}
import models.Student;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Created with IntelliJ IDEA.
 * User: sean
 * Date: 17/11/13
 * Time: 7:32 PM
 * To change this template use File | Settings | File Templates.
 */
object Students extends Controller {

  def index = Action {
    val carouselFeatures: List[Featurette] = List(
      new Featurette(
        "Solve some Interesting Real World Problems",
        """Our platform makes it easy for you to solve challenges and showcase your skills to companies. Solve problems
          |that interest you and relate to your skills and show us your creativity!
        """.stripMargin,
        "Sign up today",
        imageLink = routes.Assets.at("images/slide-01.jpg").toString(),
        buttonLink = "#signup",
        classes = "active"
      ),
      new Featurette(
        "Earn Rewards and Recognition",
        """SmarterPool is about building a portfolio based on skills, so companies know you can execute. If companies
          |like your work, you will be rewarded in cash, prizes or even employment with the company!
        """.stripMargin,
        "Sign up today",
        imageLink = routes.Assets.at("images/slide-02.jpg").toString(),
        buttonLink = "#signup"
      ),
      new Featurette(
        "Find Career Resources",
        """Our platform connects you with mentors and professionals in companies so you learn more about your career,
          |the needs of the companies you like, and skills required to succeed.
        """.stripMargin,
        "Sign up today",
        imageLink = routes.Assets.at("images/slide-03.jpg").toString(),
        buttonLink = "#signup"
      )
    )
    val carousel = new Carousel(carouselFeatures)

    val marketingFeatures: List[Featurette] = List(
      new Featurette(
        "What is SmarterPool?",
        """SmarterPool is a an online platform that connects students with companies through real world challenges. This
          |solution was created as a result of the high student/graduate unemployment and lack of corporate innovation
          |happening in India. By posting challenges, companies identify students based on skills, and use a larger,
          |smarter pool, to collaborate on innovative solutions. Students find challenges that resonate with their
          |interests and studies, and earn temporary income for their work.
        """.stripMargin
      ),
      new Featurette(
        "What's in it for me?",
        """SmarterPool targets medium and large sized companies that are looking to innovate. As a student, this
          |platform allows you to notice some of the challenges companies are facing and show how you can help. This
          |allows you to see what skills you need to learn to work at companies you like, and create a portfolio which
          |you can show to any employer of work you can do. You also earn rewards and get access to career advisors and
          |mentors. See our FAQ if you have more questions.
        """.stripMargin
      ),
      new Featurette(
        "",
        "",
        button = "See Our FAQ",
        buttonLink = routes.Companies.faq.toString()
      )
    )
    val marketing = new Marketing(marketingFeatures, 2)

    val howItWorks: List[Featurette] = List(
      new Featurette(
        "Companies Post Challenges",
        "",
        imageLink = routes.Assets.at("images/compete.png").toString()
      ),
      new Featurette(
        "Students submit ideas and solutions",
        "",
        imageLink = routes.Assets.at("images/idea.png").toString()
      ),
      new Featurette(
        "Students earn rewards and recognition for their work",
        "",
        imageLink = routes.Assets.at("images/awards.png").toString()
      )
    )
    val howItWorksMarketing = new Marketing(howItWorks, 3)

    val features: List[Featurette] = List(
      new Featurette(
        "Oh yeah, it's that good.",
        "Sign up to view the complete list of companies that we are partnered with.",
        tagline = "See for yourself."
      )
    )
    val feature = new Features(features)
    Ok(views.html.base("Students.")(carousel.Html)(marketing.Html + howItWorksMarketing.Html + feature.Html + views.html.signup.student_signup.apply())("students"))
  }

  def signUp = Action { request =>
    val fullName = request.body.asFormUrlEncoded.get("fullName") mkString ""
    val password = request.body.asFormUrlEncoded.get("password") mkString ""
    val hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt())
    val school = request.body.asFormUrlEncoded.get("school") mkString ""
    val email = request.body.asFormUrlEncoded.get("email") mkString ""
    val major = request.body.asFormUrlEncoded.get("major") mkString ""
    var student =  new Student(fullName = fullName, password = hashedPassword, school = school, email = email, major  = major)
    var success = student.write()
    if (success == None) {
      controllers.Status.failure
    } else {
      controllers.Status.success
    }
  }
}
