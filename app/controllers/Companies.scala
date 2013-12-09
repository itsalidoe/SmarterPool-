package controllers

import play.api.mvc._
import controllers.bootstrap.{Features, Marketing, Carousel, Featurette}
import play.api.templates.Html
import models.Company

object Companies extends Controller {
  
  def index = Action {
    val carouselFeatures: List[Featurette] = List(
      new Featurette(
        "Innovate by challenging a larger talent pool.",
        "Our platform allows you to pose real world challenges to larger community. Smarter Answers, Smarter Ideas, More Innovation, More Results.",
        "Learn More",
        buttonLink = "#challenge",
        imageLink = routes.Assets.at("images/slide-01.jpg").toString(),
        classes = "active"
      ),
      new Featurette(
        "Leverage Skills and Ideas without commitment",
        "Find and create solutions to business situations through our collaborative platform without contracts or costs of training and development.",
        "Learn More",
        buttonLink = "#analytics",
        imageLink = routes.Assets.at("images/slide-02.jpg").toString()
      ),
      new Featurette(
        "Hire the best",
        """Find the best candidates based on their portfolio of submissions and our
          |in depth analytic tools. Isolate based on any category, skill, school and much more!
        """.stripMargin,
        "Learn More",
        buttonLink = "#students",
        imageLink = routes.Assets.at("images/slide-03.jpg").toString()
      ),
      new Featurette(
        "Expose your Brand and Company",
        """Use our platform as a virtual career fair to exhibit your company and the types of skills and personalities
          |you are looking for in your employees.
        """.stripMargin,
        "Learn More",
        buttonLink = "#smartpool_advertise",
        imageLink = routes.Assets.at("images/slide-02.jpg").toString()
      )
    )
    val carousel = new Carousel(carouselFeatures)

    val marketingFeatures: List[Featurette] = List(
      new Featurette(
        "What is SmarterPool?",
        """SmarterPool is a an online platform that connects students with companies through real world challenges.
           This solution was created as a result of the high student unemployment and lack of corporate innovation
           happening in India.  By posting challenges, companies identify students based on skills, and use a larger,
           smarter pool, to collaborate on innovative solutions.
        """
      ),
      new Featurette(
        "How Smart is the Pool?",
        """Although we are just launching, our current pool consists of over 1000 students from institutions such as
          |IIT, IIM, BITS and many more.  These students have studied a range of disciplines; have diverse work
          |experiences, and unique skillsets.  They want to build the skills needed for the jobs they want, and they
          |want to see a prosperous India in the years to come.
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

    val features: List[Featurette] = List(
      new Featurette(
        "Post Challenges",
        """Use SmarterPool to create innovation or business challenges that can keep your company competitive.  Our
          |candidates can create solutions to problems you present at a cheaper price with a larger input.  Expect
          |submissions in 2 weeks or less without paying for training or hiring.
        """.stripMargin,
        imageLink = routes.Assets.at("images/screenshots/competition.png").toString(),
        classes = "withImage",
        id = "challenge"
      ),
      new Featurette(
        "Robust Analytics",
        """Manage your challenges in real time with our analytics tool.  This will help you see current results,
          |engagement of students, issues with your challenge, and much more.  Easily export the information to show
          |your colleagues.
        """.stripMargin,
        imageLink = routes.Assets.at("images/screenshots/challenge_analytics.png").toString(),
        imagePull = "right",
        classes = "withImage",
        id = "analytics"
      ),
      new Featurette(
        "Gain visibility",
        """List basic information about your company so students can learn more about the work, culture, and skills
          |required to be hired.  Companies can also see which students have viewed the profile and are interested in
          |applying for future reference.
        """.stripMargin,
        imageLink = routes.Assets.at("images/screenshots/company_information.png").toString(),
        classes = "withImage",
        id = "smarterpool_advertise"
      ),
      new Featurette(
        "Focus on the talent",
        """Analyze everything about each of the candidates on the website, even if they have not applied to the challenge.
          |Browse their experiences, goals, resume and glimpse into their skills through the portfolio of their submissions.
        """.stripMargin,
        imageLink = routes.Assets.at("images/screenshots/company_screening.png").toString(),
        imagePull = "right",
        classes = "withImage",
        id = "students"
      ),
      new Featurette(
        "Oh yeah, it's that good.",
        "Sign up to talk to an agent about how SmarterPool can help you hire from a smarter pool of candidates.",
        tagline = "See for yourself."
      )
    )
    val feature = new Features(features)
    Ok(views.html.base("Companies.")(carousel.Html)(marketing.Html + feature.Html + views.html.signup.company_signup.apply())("companies"))
  }

  def signUp = Action { request =>
    val companyName = request.body.asFormUrlEncoded.get("company") mkString ""
    val field = request.body.asFormUrlEncoded.get("field") mkString ""
    val email = request.body.asFormUrlEncoded.get("email") mkString ""
    var company =  new Company(companyName = companyName, field = field, email = email)
    var success = company.write()
    if (success == None) {
      controllers.Status.failure
    } else {
      controllers.Status.success
    }
  }

  def faq = Action {
    // TODO: Read FAQ file, and fill it out.
    Ok(views.html.base("companies.")(new Html(new StringBuilder))(views.html.faq())("faq"))
  }

  def contact = Action {
    Ok(views.html.base("companies.")(new Html(new StringBuilder))(views.html.contact())("contact"))
  }
}