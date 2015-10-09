(ns lakewoodpca.core
  (:use [hiccup.core]
        [hiccup.page]
        [hiccup.form]
        [hiccup.util])
  (:gen-class))

(require '[hiccup-bridge.core :as hicv])

;;; Standard global variables
(def domain "lakewoodpres.com")
(def home-page "index.html")
(def last-page "contact-us.html")
(def school "Lakewood Presbyterian School")
(def email "allthewayhome@sbcglobal.net")
(def phone "214-321-2864")
(def address1 "7020 Gaston Avenue")
(def address2 "Dallas, TX 75214")
(def quote "Train up a child in the way he should go, And when he is old he will not depart from it.")
(def quote-source "Proverbs 22:6")

(def headmaster "Arnie Robertstad")
(def elementary-principal "Edie Robertstad")
(def elementary-admissions-director "Janice Gilbert")
(def secretary "Nina Vail")

;;Social
(def facebook "https://www.facebook.com/lakewood.presbyterian")

(def embed-google-map
  [:iframe
   {:src
    "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3353.1525332960196!2d-96.73630469999999!3d32.81472319999999!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x864ea1f0de6e45f7%3A0x729572b600689065!2s7020+Gaston+Ave%2C+Dallas%2C+TX+75214!5e0!3m2!1sen!2sus!4v1444335292567",
    :width "500",
    :height "450",
    :frameborder "0",
    :style "border:0",
    :allowfullscreen "allowfullscreen"}])
  
;;; Link generating stuff
(defn get-page
  "Accessor to find an item in the vector of vectors."
  [filename all-pages]
  (first (filter (fn [i] (= (first i) filename)) all-pages)))

(defn gen-menu-link
  "Returns the appropiate style of link. If the target page is the current one, 
  it adds a different marking."
  [target-page current-page]
  (if (= target-page current-page)
    [:li.current [:a {:href (first current-page)} (second current-page)]]
    [:li         [:a {:href (first current-page)} (second current-page)]]))

(defn gen-menu [filename all-pages]
  (let [target-page (get-page filename all-pages)]
    [:ul
     (map (fn [i] (gen-menu-link target-page i)) all-pages)]))

(defn gen-footer-link
  [last-page current-page]
  ;; crying for a macro...
  (if (= last-page current-page)
    [:li.last [:a {:href (first current-page)} (second current-page)]]
    [:li      [:a {:href (first current-page)} (second current-page)]]))

(defn gen-footer-menu [last-filename all-pages]
  (let [target-page (get-page last-filename all-pages)]
    [:ul
     (map (fn [i] (gen-footer-link target-page i)) all-pages)]))

;;; Boilerplate content generators
(defn gen-head []
  [:head
     [:title school]
     [:meta {:charset "UTF-8"}]
     [:link
      {:rel "stylesheet", :type "text/css", :href "styles/style.css"}]
   "<!--[if IE 6]><link rel=\"stylesheet\" type=\"text/css\" href=\"styles/ie6.css\" /><![endif]-->"])

(defn gen-banner []
  [:div#section
   [:div
    [:a
     {:href "index.html"} ; stays the same
     [:img {:src "images/logo.gif", :alt ""}]]]
   [:span address1
    [:br] address2
    [:br] phone
    [:br] email]
   ])

(defn standard-header [filename all-pages]
  [:div#header
   (gen-banner)
   (gen-menu filename all-pages)])

(defn index-header [filename all-pages]
  [:div#header
   (gen-banner)
   (gen-menu filename all-pages)
   [:div#tagline
    [:div
     [:h4 quote-source]
     [:p quote]]]])

(defn gen-footer [filename all-pages]
  [:div#footer
   [:div
    [:div#connect " " ; these spaces are on purpose, don't remove!
     [:a {:href facebook}
      [:img {:src "images/icon-facebook.gif", :alt ""}]]
     " "
     [:a {:href "#"}
      [:img {:src "images/icon-twitter.gif", :alt ""}]]
     " "
     [:a {:href "#"}
      [:img {:src "images/icon-youtube.gif", :alt ""}]]
     " "]
    [:div.section
     (gen-footer-menu last-page all-pages)
     [:p "Copyright © " [:a {:href "#"} domain] " - All Rights Reserved"]]]])

;;; Actual pages for the website.
;;; Since the index page is unique, I won't create a macro

(defn index-page [filename all-pages]
  [:html
   (gen-head)
    [:body
     [:div#page
      (index-header filename all-pages)
      [:div#content
       [:div#home
        [:div
         [:div#aside
          [:div
           [:h1 "Welcome to " [:span.last school]]
           [:a {:href "#"} [:img {:src "images/model1.jpg", :alt ""}]]
           [:p
            "Lakewood Presbyterian School (LPS) is a Christian school offering instruction in grades kindergarten through twelve. It is a ministry of the Lakewood Presbyterian Church (PCA) and operates under the authority of the Session, the governing board, of that Church. The Church is committed to the Westminster Confession of Faith, together with the Larger and Shorter Catechisms. Under the Bible, these provide the perspective from which we teach. The pastor of the Church is also the headmaster of the school. Teachers are approved by the Session."]]
          [:ul
           [:li
            [:h2 [:a {:href "#"} "PERSPECTIVE " [:span.last "AND PURPOSE"]]]
            [:p
             "God has given to parents and to the Church the responsibility to teach their children. The Bible states the goal of the nurture of children in several ways: to love God with heart, mind, soul and strength; to possess and grow in wisdom; to be disciples who observe all things that the Lord commanded them; to exercise a godly dominion over creation; to take every thought captive to Christ; to love their neighbor as themselves; and to be conformed to the image of Christ."]
            [:p "At the heart of the task of Christian education are God’s Word, the Bible, and instruction in it. It is God’s authoritative statement of truth. Skills must be developed and understanding gained in light of God’s Word. Christian education must develop the natural and spiritual gifts of the students. It needs to impart to the students an understanding of God’s world, His creation, which He sovereignly rules and governs for our good and His glory. And it should advance the students’ understanding of the hearts of men."]
            [:p "Lakewood Presbyterian School does not presume to accomplish this task alone. It assumes that the student’s family and Church will carry the primary weight of spiritual formation. The school intends to conduct the intellectual training of its students in ways consistent with and supportive of the long Christian tradition of the West and practices of faithful parents and Churches."]
            [:p "The experience of twenty years of conducting the school has shown us that the best predictor of the success of a student at  Lakewood is his discipline.  A sufficient combination of self-discipline and parentally  imposed discipline will be necessary to complete the required work"]]
           [:li
            [:h2
             [:a
              {:href "#"} [:span.last "PROSPECTS FOR SUCCESS"]]]
            [:p
             "Experience has shown us that the best predictor of the success of a student at Lakewood is his discipline. A sufficient combination of self-discipline and parentally imposed discipline will be necessary to complete the required work."][:br][:br]]
           [:li
            [:h2
             [:a
              {:href "#"} [:span.last "FACULTY"]]]
            [:p
             [:ul
              [:li "Headmaster: " headmaster]
              [:li "Elementary Principal: " elementary-principal]
              [:li "Admissions Director: " elementary-admissions-director]
              [:li "Secretary: " secretary]
              ]]]
            [:li
             [:p [:ul [:li "Phone: " phone] [:li "Email: " email]]]
             [:p "Address: " [:ul [:li address1] [:li address2]]]
             [:p "Open on " [:a {:href "https://goo.gl/maps/3nGr3gXAYy52"} "Google Maps"]]]
           ]]
         [:div#calendar
          [:h3 "School Calendar"]
          [:ul
           [:li
            [:div
             " "
             [:span "1" [:br] "\n                Jan"]
             [:h2 [:a {:href "#"} "Not Implemented Yet."]]]
            [:p
             "Calendar coming soon."]]
           ]]
         ]]]
      (gen-footer filename all-pages)
      ]]])

;;; Other pages

(defmacro standard-page
  "Wrapper around standard pages for the template since 
  they begin after :div#page."
  [filename all-pages & content]
  `[:html
    (gen-head)
    [:body
     [:div#page
      (standard-header ~filename ~all-pages)
      [:div#content [:div ~@content]]
      (gen-footer ~filename ~all-pages)]]])

(defn about-page [filename all-pages]
  (standard-page
   filename
   all-pages
   [:h3 "About " school]
   [:div.first
    " "
    [:a {:href "#"} [:img {:src "images/model1.jpg", :alt ""}]]
    [:h1 "CURRICULUM"]
    [:p
     "At the elementary level our classes concentrate on gaining basic skills and on mastering essential facts."]]
   [:div
    [:h2 "KINDERGARTEN THROUGH 2ND GRADE"]
    [:p
     "Our four and five year old kindergarten class is a one or two year program, depending upon the age a child enrolls. Children must be four by July 1st to begin the four year old kindergarten and five to start our five year old kindergarten. Children will be accepted based upon the school’s determination of a child’s readiness. In kindergarten and grades one and two, phonics, reading and writing skills, and math are the main subjects taught. Science, history, geography, music, and art are introduced. A weekly music class teaches singing and enjoyable rhythmic activities. The children get to perform in music programs twice a year. Once a week the boys have a sports class while the girls take ballet."]]
   [:div
    [:h2 "3RD THROUGH 6TH GRADES"]
    [:p
     "In the middle and upper elementary grades the subjects are:"
     [:ul
      [:li [:h4 "Arithmetic:"] "We use the Saxon curriculum. We place each
student according to his math level."]
      [:li [:h4 "English:"] "We emphasize grammar and the reading of classical literature."[:p]]
      [:li [:h4 "History and Geography:"] "This is taught from a classical Christian worldview. We survey history from biblical to modern times, noting major people, events, and dates. We teach both United States and world geography."]
      [:li [:h4 "Science:"] "We begin the exploration of God’s creation."]
      [:li [:h4 "Art:"] "Art is integrated into various classes. A weekly music class prepares the students for public programs in the winter and spring."]]]]
   [:div
    [:h2 "7th THROUGH 12TH GRADES"]
    [:p
     "The secondary classes (grades 7 – 12) aim at understanding and expression in the various subjects."
     [:ul
      [:li [:h4 "Literature and History:"] "These classes all include composition. They proceed through the following eras: Biblical (7th and 8th); Classical, Patristic, and Medieval (9th and 10th); and Reformation, Enlightenment, and Modern (11th and 12th)."]
      [:li [:h4 "Science:"] "The classes are: Horticulture (7th and 8th), Physical Science and Biology (9th and 10th), and Chemistry and Physics (11th and 12th)."[:p]]
      [:li [:h4 "Mathematics:"] "We use the Saxon textbooks and place the student according to his mathematical competence rather than by grade level. The courses range from pre-algebra through analytical geometry and trigonometry."]
      [:li [:h4 "Latin:"] "The Latin classes progress from learning the rudiments of the language through reading the literature of classical and ecclesiastical Latin.."]
      [:li [:h4 "Music:"] "Two levels of classes involve students in the fundamentals of music and its history through appreciation and participation."]]
     [:br][:br]
     [:p "We conduct a drama class, select choir, athletics and a student produced yearbook throughout the year at times outside of our regular hours."]
     [:p "The school maintains transcripts only for high school level work, which can count towards graduation."]
     ]]
   [:div
    [:h2 "THE SCHEDULE"]
    [:p "The school year is divided into trimesters that begin the Tuesday after Labor Day and continue through the fourth week of May. Vacations include the week of Thanksgiving, three weeks at Christmas, the week between Palm Sunday and Easter, Texas Independence Day, and a day in early May for the school play. Additional days off may be announced as needed."][:br]
    [:p "Classes meet between 8:00am and 12noon, Tuesday through Friday. Our classes utilize a pairing of grades (i.e. 1st & 2nd, 3rd & 4th, etc.). Due to the shorter school day, we place a heavier emphasis on homework than would a daylong school. The homework is lighter in the lower grades, but increases as the student matures."]]
   [:div
    [:h2 "FINANCES"]
    [:p "Tuition: Both elementary and secondary tuition will be $230.00 a month for 9 months. A $20 discount is given for second and subsequent students in the same family."]
    [:br]
    [:p "Student Fee: All students will pay a general fee of $460."]
    [:br]
    [:p "The payments are due for continuing students as follows:"][:br]
    [:ul
     [:li "1st payment of ½ Fee due May 1st – $230"][:br]
     [:li "2nd payment of ½ Fee due June 1st – $230"][:br]
     [:li "First Tuition payment due July 1st – $230"][:br]
     [:li "Second Tuition payment due August 1st – $230"][:br]]
    [:p "Tuition payments are due the first school day of each month thereafter through March."]]
   [:div
    [:h2 "ENROLLMENT PROCESS"]
    [:p "Inquiries about admission should be made to the office of the school. Prospective parents will set an appointment to observe the school. Then, parents must submit applications for all students and transcripts, for secondary students entering the 10th, 11th, or 12th grades. An interview with the elementary admissions director or headmaster, including placement testing (if needed), will be conducted. If mutually acceptable, parents, students, and the school official will sign the enrollment covenant and the parents will pay the student fees and the first month’s tuition, at which time the student(s) may be considered enrolled. All monies paid are non-refundable."]]
   [:div
    [:h2 "STUDENT CODE OF BEHAVIOR"]
    [:p "Student behavior will be held to the standard of the Law of God, as expressed summarily in the two great commandments and in the Ten Commandments. While each individual is responsible for his own behavior, those in positions of authority (the teachers and administrators of the school) are also responsible “to endeavor that it (what is forbidden or commanded to ourselves) may be avoided or performed by others, according to the duty of their places.” Larger Catechism, 99"][:br]
    [:p "For this reason the faculty of the school will work with the student in keeping with the Larger Catechism, 129:"][:br]
    [:p.span.big-quote "It is required of superiors, according to that power they receive from God, and that relation wherein they stand, to love, pray for, and bless their inferiors: to instruct, counsel, and admonish them; countenancing, commending, and rewarding such as do well, and discountenancing, reproving,  and  chastising such as do ill; protecting and providing for them all things necessary for soul and body; and by grave, wise, holy and exemplary carriage, to procure glory to God, honor to them-selves so to preserve that authority which God hath put upon them."][:br]
    [:h4 "Of the students the following is expected:"]
    [:p
     [:ul
      [:li "The honor which inferiors owe to their superiors is, all due reverence in heart, word, behavior; prayer and thanksgiving for them; imitation of their virtues and graces; willing obedience to their lawful commands and counsels; due submission to their corrections; fidelity to, defense and maintenance of their persons and authority, according to their several ranks, and the nature of their places; bearing with their several ranks, and the covering them, in love, so that they may be an honor to them and to their government. Larger Catechism, 127"][:br]
      [:li "Attitudes and behavior to be avoided by students include: The sins of inferiors against their superiors are, all neglect of the duties required toward them; envying at, contempt of, and rebellion against their persons and places, in their lawful counsels, commands and corrections; cursing, mocking, and all such refractory and scandalous carriage, as proves a shame and dishonor to them and their government. Larger Catechism, 128"]
      [:li "Students will always be on their honor to do their work according to the rules of the assignment."][:br]
      [:li "The elementary grades (K – 6) are required to wear uniforms."][:br]
      [:li "The dress of secondary students is regulated to this extent:"][:br]
      [:li "Girls: Blouses or sweaters must be long enough to be tucked in. They must be opaque. Shirts must come to the base of the neck. Dresses or skirts must be to mid-knee. Slacks must be worn at the waist; leggings are not considered trousers or pants but hosiery. Shorts are not permitted."][:br]
      [:li "Boys: Shirts must be long enough to be tucked in. They must be opaque. No “tank tops.” Pants must be worn at the waist. Shorts are not permitted."][:br]
      [:li "Radios, MP3 players, tape players, CD players, cell phones and other electronic devices (excepting calculators and computers) may not be brought into the school."][:br]
      ]]
    [:h4 "Particular instructions may be given for situations not covered by these rules."]
    [:p "By accepting enrollment in the school the student is giving his willing consent to be held to these standards under the ministry of the faculty. Should discipline beyond the verbal prove necessary, the student may expect to be removed from class or sent home. Upon major breaches of the standards or contumacious behavior, the student may (after conference with parents) be suspended or expelled."]
    ]
   [:div
    [:h2 "PROSPECTS FOR SUCCESS"]
    [:p "Experience has shown us that the best predictor of the success of a student at Lakewood is his discipline. A sufficient combination of self-discipline and parentally imposed discipline will be necessary to complete the required work."]]
   ))


(defn admissions-page [filename all-pages]
  (standard-page
   filename
   all-pages
   [:h3 "Admissions"]
   [:div.first
    " "
    [:a {:href "#"} [:img {:src "images/model1.jpg", :alt ""}]]
    [:h1 "ENROLLMENT FORM"]
    [:p
     "Enrollment Forms must be filled out and submitted to the office for consideration of admission. The completed form places a child on the 'wait' list. Once approval has been met and the appropriate monies received the student’s place is secured. You may drop this off in the school office during school hours OR mail it to the school. Money is not required until the spot has been offered to the student."]
    [:br]
    [:p "Download the enrollment form "
     [:a {:href "files/EnrollmentApplication.pdf"} "HERE."]]
    ]
   ))

(defn elementary-page [filename all-pages]
  (standard-page
   filename
   all-pages
   [:h3 "Elementary"]
   [:div.first
    " "
    [:a {:href "#"} [:img {:src "images/model1.jpg", :alt ""}]]
    [:h1 "SUMMARY"]
    [:p "At the elementary level our classes concentrate on gaining basic skills and on mastering essential facts. Our four and five year old kindergarten class is a one or two year program, depending upon the age a child enrolls. Children will be accepted based upon the school’s determination of a child’s readiness. In kindergarten and grades 1st and 2nd, phonics, reading and writing skills, and math are the main subjects taught. Science, history, geography, music , and art are introduced. A weekly music class teaches singing and enjoyable rhythmic activities. The children get to perform in music programs twice a year. Once a week the boys have a sports class while the girls take ballet. There is a ballet recital each spring."][:br]
    [:p "In the 3rd through 6th grades, the subjects are math, English, history and geography, and science. In math, we use the Saxon curriculum. English emphasizes grammar and the reading of classical literature. History and geography are taught from a classical Christian world view. We survey history from biblical to modern times, noting major people, events, and dates. We teach both United States and world geography. In science, we begin the exploration of God’s creation. Art is integrated into various classes. A weekly music class prepares the students for public programs in the winter and spring."][:br]
    [:h1 "ELEMENTARY SUPPLY LIST"]
    [:p "Parents, please go over the supply list and make sure that each day your child comes to class prepared."]
    [:h4 "All elementary students please bring on the first day of school the following:"]
    [:ul
     [:li "One roll of paper towels"]
     [:li "One box of kleenex"]
     [:li "One package of sanitizing wipes"]]
    [:h4 "Kindergarten – Materials supplied by school (please, no backpacks, snacks, or toys except on Show & Tell Day)"]
    [:h4 "1st & 2nd grades –"]
    [:ul
     [:li "a small to medium sized sturdy backpack"]
     [:li "school will provide other materials including tablet paper, art supplies, and filled pencil and crayon boxes for each child"]
     [:p "Please buy and maintain the following at home in order to complete homework:"]
     [:ul
      [:li "½” tablet paper, #2 lead pencils, crayons, glue, and scissors"]
      [:li "1st grade – addition & subtraction flashcards"]
      [:li "2nd grade – addition, subtraction, & multiplication flashcards"]]]
    [:h4 "3rd & 4th grades –"]
    [:ul
     [:li "a large, sturdy backpack"]
     [:li "2 pk wide ruled notebook paper (this paper will be collected for in-class assignments only, please buy extra for your own work throughout the year)"]
     [:li "pens – dark blue or black, only (3rd grade optional or erasable)"]
     [:li "red checking pencil or pen"]
     [:li "thin markers"]
     [:li "ruler"]
     [:li "colored pencils"]
     [:li "protractor and compass (3rd graders only)"]
     [:li "scissors"]
     [:li "glue stick"]
     [:li "crayons"]
     [:li "pencil bags"]
     [:li "pencils (students need to come to school each day with at least 3 sharpened pencils)"]
     [:li "binders will be supplied for the 3rd through 6th grades. Please do not bring any binders from home!"]]
    [:h4 "5th & 6th grades-"]
    [:ul
     [:li "a large, sturdy backpack"]
     [:li "2 pk notebook paper (this paper will be collected for in-class assignments only, please buy extra for your own work throughout the year)"]
     [:li "pencil bags"]
     [:li "pencils (students need to come to school each day with at least 3 sharpened pencils)"]
     [:li "ruler, protractor and compass to be used at home"]
     [:li "binders will be supplied for the 3rd through 6th grades. Please do not bring any binders from home!"]][:br]
    [:h1 "ELEMENTARY SCHOOL CARPOOL PROCEDURES"]
    [:p "Lakewood Presbyterian will begin our carpool pick-up on the first day of school.  Please look over the attached map which explains traffic flow and pick-up procedures.  This carpool is for pick-up only and mandatory for all elementary students, kindergarten through sixth grade.  Families with both elementary and secondary students need to arrange with their secondary children where they will pick them up.  If no family arrangements are made, the secondary students will be instructed to stand outside in the front of the building. Here are the procedures:"
     [:ul [:br]
      [:li "Each family is receiving several 'name' cards.  Please place one in each vehicle that is used to pick up your child.  They should be placed in the driver side window so the carpool volunteer can clearly see the name."][:br]
      [:li "Please follow the times carefully.  Carpool will begin at 11:50 and should run until no later than 12:05.  Please realize that you are responsible to be in the carpool lane at 11:50.   If for some reason you are not able to be in the carpool lane at that time, please ask a friend to pick up your children and hold them until you are able to be there."][:br]
      [:li "If someone else is picking up your child and does not have a “name” card. please call the office and let us know who will be picking up your child."][:br]
      [:li "If you need to go into the school for some reason, please arrive at school a little early, park in the side lot, and enter the school.  You can then pick your child up from the foyer where he will be waiting."][:br]
      [:li "If you need to speak to your child’s teacher, please call the school office during school hours and set up an appointment, or email the teacher to do so."][:br]
      ]][:br]
    [:h1 "ELEMENTARY SCHOOL SUMMER READING"]
    [:h4 "Entering Kindergarten"]
    [:ul [:li "No required books.  Read to your child from a variety of topics to expand his interests."]]
    [:h4 "Entering First Grade (NO BOOK REPORTS DUE)"]
    [:p "Beginning Readers:"]
    [:ul
     [:li "Bob Books"]
     [:li "Little Bear books by Elise Minarik"]
     [:li "Dr. Suess books"]
     [:li "Amelia Bedelia books by Peggy Parish"]
     [:li "The Real Mother Goose"]
     [:li "The Little House by Virgina Burton"]
     [:li "Selected books and activities from Veritas Literature (call the school and leave a message if you would like a packet)"]]
    [:h4 "Entering Second Grade"]
    [:ul
     [:li "Childhood of Famous Americans series (choose any)"]
     [:li "Encyclopedia Brown books by Donald Sobol"]
     [:li "Beatrix Potter books"]
     [:li "The Boxcar Children series by Gertrude Warner"]
     [:li "Little House in the Big Woods by Laura Wilder"]
     [:li "The Millers Series by Mildred A. Martin"]
     [:li "Biographies by INgi & Parin D’ Aulare"]
     [:li "The Hundred Dresses by Eleanor Estes"]
     [:li "The Matchbox Gunby Walter D. Edmonds"]
     [:li "The Courage of Sarah Nobleby Alice Dagliesh"]]
    [:h4 "1st & 2nd Grade Book Report Form"]
    [:p "Download " [:a {:href "files/2ndgradebookreportforms.pdf"} "HERE."]]
    [:p "(this is NOT to be used for school year book reports!)"]
    [:h4 "Entering Third & Fourth Grades"]
    [:ul
     [:li "Mr Popper’s Penguinsby Richard Atwater"]
     [:li "The Time Machine Illustrated Classic Series (Troll) by H G Wells"]
     [:li "Alice in Wonderland by Lewis Carroll"]
     [:li "The Fairy Books by Andrew Lang"]
     [:li "Charlie and Chocolate Factoryby Roald Dahl"]
     [:li "Little House series by Laura Wilder"]
     [:li "Owls in the Familyby Farley Mowat"]
     [:li "Pinocchioby Carlos Collodi"]
     [:li "Romona books by Beverly Cleary"]
     [:li "The Cricket in Times Squareby George Selden"]
     [:li "The Sign of the Beaver by Elizabeth Sphere"]
     [:li "The Wizard of Ozbooks by L. FrankBaum"]
     [:li "Caddie Woodlawn by Carie Brink"]
     [:li "Wind in the Willowsby Kennet Grahame"]
     [:li "A Little Princessby Frances Hodgson Burnett"]
     [:li "Heidiby Johanna Spyri"]
     [:li "Rosa(Abeka) by Elaine Cunningham"]
     [:li "Pollyanna by Eleanor Porter"]
     [:li "The Incredible Journeyby Sheila Burnford"]
     [:li "Swiss Family Robinsonabridged (Abeka) by Johann Wyss"]
     [:li "Black Beautyby Anna Sewell"]
     [:li "The Secret Gardenby Frances Hodgson Burnett"]
     [:li "Flaming Arrows by William Steele"]
     [:li "Winter Danger by William Steele"]
     [:li "The Lone Huntby Willian Steele"]
     [:li "The Buffalo Huntby Willian Steele"]]
    [:h4 "3rd & 4th Grade Summer  Book Report form"]
    [:p "Download " [:a {:href "files/3rdand4thgradebookreportform.pdf"} "HERE."]]
    [:p "(may be used for monthly book reports during school year)"]
    [:h4 "Entering Fifth & Sixth Grades"]
    [:ul
     [:li "Little Woman by Louisa Alcott"]
     [:li "Little Men by Louisa Alcott"]
     [:li "Eight Cousins by Louisa Alcott"]
     [:li "Old Yeller by Fred Gibson"]
     [:li "Anne of Green Gables series by L.M. Montgomery"]
     [:li "Around the World in Eighty Days by Jules Verne"]
     [:li "20,000 Leagues Under the Sea by Jules Verne"]
     [:li "Red Wall by Brian Jacques"]
     [:li "Martin the Warrior by Brain Jacques"]
     [:li "Mossflower by Brian Jacques"]
     [:li "The Prydian Chronicles by Lloyd Alexander"]
     [:li "Historical fiction novels by Elizabeth Sphere"]
     [:li "The Light in the Forest by Conrad Richter"]
     [:li "The Baronet’s Song by George McDonald"]
     [:li "At the Bakc of the North Wind by George McDonald"]
     [:li "Any book from The Farm Mystery Series"]
     [:li "Any biography (check you public library)"]]
    [:h4 "5th & 6th Grade Summer Book Report Form"]
    [:p "(this is for the Summer Book Report ONLY!)"]
    [:p "Download " [:a {:href "files/5th6thgradebookreportforms.pdf"} "HERE."]][:br]
    [:h1 "BALLET"]
    [:p "To the K4, K5, 1st and 2nd Grade Girls’ Parents:"][:br]
    [:p "Ballet Classes will again be on Tuesdays starting the first day of school."][:br]
    [:p "Dance attire is a Black Leotard and Pink Tights and Pink Ballet Shoes.  Please have the girls wear their tights to school with their uniforms."][:br]
    [:p "\"Miss\" Cherrilane will be teaching the classes again this year.  Mrs. Blackburn began teaching in 1968 and had Cherrilane School of Dance in Lakewood until 2005 during which time she taught the Robertstad girls and Jessica and Doris Gradle.  She has choreographed for Six Flags over Georgia, Six Flags over Texas, Astroworld, Fontana Village, Clayton Community College, JL Long Middle School, Metro Players, and Lyric Stage and is a Life-time member of Dance Educators of America.  She is mom to Anabelle in the 9th  grade and teaches with Mrs. Robertstad in the 1st/2nd grades."][:br]
    [:p "Contact Info:  cell # 214-862-0383, e-mail:  zagnut@sprynut.com"]
    [:h1 "SYMPHONY FIELD TRIP FOR 3RD THROUGH 6TH GRADES"]
    [:p "Please print and return this form to the school office.  Check is required for an NON STUDENT tickets.  Student Tickets are covered in the Student Fee."]
    [:p "Download " [:a {:href "files/permissionslip.pdf"} "HERE."]][:br]
    ] ; end of content
   ))

(defn secondary-school-page [filename all-pages]
  (standard-page
   filename
   all-pages
   [:h3 "Secondary School"]
   [:div.first
    " "
    [:a {:href "#"} [:img {:src "images/model1.jpg", :alt ""}]]
    [:h1 "SUMMARY"]
    [:p "Grammar, Composition, Greek Literature, Roman Literature, Medieval Literature, Renaissance & Reformation Literature, Shakespeare, 17th & 18th Century Literature, and 19th & 20th Century Literature. History classes include substantial reading from and about each era. They include: Texas History, Bible, Greek & Roman History, Medieval History, Renaissance & Reformation History, The Age of Revolution, and Modern History."][:br]
    [:p "The secondary classes, grades 7th through 12th, aim at understanding and expression in the various subjects. Literature classes include vocabulary and composition. They are: Grammar, Composition, Greek Literature, Roman Literature, Medieval Literature, Renaissance & Reformation Literature, Shakespeare, 17th & 18th Century Literature, and 19th & 20th Century Literature. History classes include substantial reading from and about each era. They include: Texas History, Bible, Greek & Roman History, Medieval History, Renaissance & Reformation History, The Age of Revolution, and Modern History. The science classes include: Earth Science, Geography, Physical Science, Biology, Chemistry, and Physics. In math, we continue the Saxon curriculum and place the student according to his mathematical competence rather than grade level. The courses range from pre-algebra through an introduction to calculus. Latin progresses from learning the rudiments of the language through reading the literature of classical and ecclesiastical Latin. We conduct a theatre class, secondary choir, and journalism class throughout the year at times outside of our regular hours."]
    [:h4 "THE SCHOOL MAINTAINS TRANSCRIPTS ONLY FOR 9th THROUGH 12th GRADES."][:br][:br]
    [:h1 "SECONDARY CURRICULUM"]
    [:p "The secondary classes (grades 7 – 12) aim at understanding and expression in the various subjects."][:br]
    [:ul
     [:li "Literature and History: These classes all include composition. They proceed through the following eras: Biblical (7th and 8th); Classical, Patristic, and Medieval (9th and 10th); and Reformation, Enlightenment, and Modern (11th and 12th)."][:br]
     [:li "Science: The classes are: Horticulture (7th and 8th), Physical Science and Biology (9th and 10th), and Chemistry and Physics (11th and 12th)."][:br]
     [:li "Mathematics: We use the Saxon textbooks and place the student according to his mathematical competence rather than by grade level. The courses range from pre-algebra through analytical geometry and trigonometry."][:br]
     [:li "Latin: The Latin classes progress from learning the rudiments of the language through reading the literature of classical and ecclesiastical Latin."][:br]
     [:li "Music: Two levels of classes involve students in the fundamentals of music and its history through appreciation and participation."]]
    [:h4 "We conduct a drama class, select choir, athletics and a student produced yearbook throughout the year at times outside of our regular hours."]
    [:p "The school maintains transcripts only for high school level work, which can count towards graduation."][:br][:br]
    [:h1 "STUDENT RULES FOR GRADES 7 – 12"]
    [:h4 "Cheating Policy"]
    [:p "If a teacher discovers that a student has plagiarized on a paper or cheated on a test, the student will be given an F for that trimester’s grade in that class.   We accept Webster’s definition of plagiarism: “The appropriating and putting forth as one’s own ideas, language, or designs of another.” Any source used must be credited. Students need to realize that teachers can and do search the internet to find sources the students may illegitimately use. Less severe, but still painful, penalties will be applied to cheating in smaller assignments or on quizzes. Being in possession of tests given in previous years and using them to prepare for a test is not permitted.   Students are required to follow the rules of any test or assignment.   Teachers do not discipline for cheating based upon the testimony of the students. It must be based upon the teacher’s own observation. However, if a student becomes aware of cheating on a paper or a test that the teacher has not observed, we ask that the student report to the teacher that there was cheating, but not to name the offender.   The teacher will not ask for the name."][:br]
    [:h4 "Students are not permitted to use cell phones or other electronic devices capable of recording or providing information (dedicated calculators excepted) in class. A ringing or vibrating phone will be confiscated and only returned to the parent. If used for cheating, the phone will be permanently confiscated and all student phones will be banned from the building."][:br]
    [:h4 "Appearance Policy"][:br]
    [:p "All students: No inappropriate language or images on clothing, no visible tattoos, no writing or drawing with ink on the skin or clothing, no body jewelry (except one earring per ear on girls), no hats worn inside, no unnatural hair color, no ratty clothing."][:br]
    [:p "Ladies: skirts or dresses to mid-knee or longer (even with tights or leggings), no shorts, no tank tops, opaque blouses long enough to be tucked in, with the collar touching the base of the neck. (Touch the collarbone. Cloth – good; skin – too low.)"][:br]
    [:p "Gentlemen: no shorts, no tank tops, opaque shirts long enough to be tucked in, with the collar touching the base of the neck (see above), pants worn above the hips."][:br]
    [:p "We can think of additional rules, if necessary. If you are inappropriately dressed, you will be given the option of returning home immediately (an unexcused absence) or purchasing from the school and putting on acceptable clothing (if available). Repeated offenders will receive more severe penalties."][:br]
    [:h4 "Excused Absence Policy"][:br]
    [:p "An excused absence entitles a student to turn in, upon his return, work due while he was absent and to take tests given during his absence on his first day back.   If an absence is unexcused, neither of these privileges is granted.   A zero is given for worked missed due to an unexcused absence.   An excused absence does not restore the opportunity to receive credit for classroom participation, daily quizzes, or science labs that were missed.   If a student takes an unexcused absence written work may be turned in on time, either by another person or by email to a teacher, for credit to be given. The responsibility for such alternate delivery rests with the student."][:br]
    [:p "LPS will excuse only those absences that we consider necessary. To receive an excuse for an absence the student must return to school with a completed Request for Excused Absence Form, which must be presented to and approved by the Headmaster. This form is available in the office or may be downloaded from the school’s website (www.lakewoodpres.com)."][:br]
    [:p "Optional absences will not be excused. The form indicates absences we regard as necessary and allows for the possibility that we have not thought of all of them.   We make explicit that an illness, to be an excuse for an absence, must keep the student inside at home all day, with the exception of a trip to the doctor.    If seeking an excused absence for reasons other than those explicitly stated on the form, the parent should consult with the Headmaster ahead of time."][:br]
    [:p "The student is free to take optional absences, but will not be allowed to turn in work late as though on time or to make up missed tests. Parents are advised to consult the school calendar before planning trips and to keep in mind the daily schedule before making medical or dental appointments. These appointments will only be excused if it is not possible to be seen outside of school hours."][:br]
    [:p "**This form must be given to and signed by Mr. Robertstad only before returning to class."][:br]
    [:p "Download " [:a {:href "files/ExcusedAbsences11.pdf"} "HERE."]][:br][:br]
    [:h1 "GRADUATION REQUIREMENTS"]
    [:p "Below are the requirements for graduation from Lakewood Presbyterian School. To avoid misunderstandings and to preserve the integrity of a diploma from the school, we consider it necessary to spell out clearly a set of standards that will need to be met. To graduate from L.P.S., a student must:"][:br]
    [:ol
     [:li "Earn 20 credits in grades 9-12. Each trimester of literature and history classes is worth one-half credit. Each trimester of all other classes is worth one-third credit. Only math classes Algebra 1 and above count towards graduation. (They are awarded credit even if passed before ninth grade.) Except as limited below, these credits may be earned at L.P.S. or transferred from another school (including home school and private lessons in the arts, athletics, or other subjects). A written record from the responsible persons, school, tutor, or parents must be submitted to Lakewood Presbyterian School by the beginning of the senior year for us to acknowledge these credits. As a guideline for private lessons, treat as a full credit any class that involves three or more hours of instruction per week for the school year; as a half-credit a class that requires less than three hours of instruction per week for the school year."][:br]
    [:li "Have among the 20 credits in grades 9-12 at least two high school level credits in each of these subject areas: mathematics (Algebra 1 & Algebra 2), history, science, literature, and foreign language."][:br]
     [:li "Provide written notice before the beginning of the senior year of intent to graduate."][:br]
     [:li "Earn at least 3 credits at Lakewood Presbyterian School in the senior year, which must include the three history and literature classes."][:br]
     [:li "Take PSAT test as a junior and the SAT as a senior. No minimum score is required."][:br]
     [:li "Be clear of material obligations to the school (e.g. tuition, returned books)."][:br]
     [:li "The student may not graduate ahead of the class with which he was admitted."]][:br][:br]
    [:h1 "SECONDARY SCHOOL ELECTIVES"]
    [:p "As you consider your student’s school schedule, please review these elective programs and classes which occur outside the normal Tuesday through Friday morning schedule.  These programs are favorable additions for a rich high-school transcript.  To enroll your child, please review the requirements, and complete the response form, which you can download " [:a {:href "files/SecondaryResponseForm1.pdf"} "HERE."] "  In order for a student to receive academic credit for Basketball, Choir, or Theatre, he must participate for the entire school year or sports season.  No credit is given for any trimester if the student does not complete the year."][:br]
    [:ul
     [:li "Basketball – Participation in the sports program does not involve try-outs; everyone is welcome to take part on the school teams.  During the basketball season, which is mid-October through February, teams practice three to four afternoons a week with no practices on game days.  There are usually two games per week, only one of which is held on a school night.  No games or practices are held on Wednesdays.  Uniforms are the property of the school, are assigned to players, and are collected at the end of the season.  Students in 9th through 12th grades receive one credit per year and the grade for this class is averaged in with the total transcript.  Grades are based upon participation.  There will be a  monthly athletic fee to cover costs.  We have not yet established the amount, but our best guess is that it will be approximately $60.00 per month during the season from October through February.  Current plans include junior high boys and girls and varsity boys and girls basketball teams."][:br]
     [:li "Choir – This class is held on Thursday afternoons at 12:00pm and is open to 7th through 12th grade students upon audition and invitation.  Students in 9th through 12th grades receive one credit per year.  Grades are based upon participation.   There are no fees charged to take this class."][:br]
     [:li "Princeps – This volunteer service program meets at least twice monthly on Tuesday afternoons from 12:00pm to 1:30pm and is open to all 7th through 12th grade students.  Although no transcript credit is given for this work, students in 9th through 12th grades receive confirmation of their participation in the group’s activities.  This program seeks to foster leadership through service.  One of the primary projects of Princeps will be the production of the yearbook."][:br]
     [:li "Theatre – This class is held on Wednesday afternoons from 12:00pm until approximately 3:30pm and is open to all 7th through 12th grade students.  Students in 9th through 12th grades receive one credit per year.  Grades are based upon class and production participation.   Our main purpose in this class is the production of the winter and spring plays.  Opportunities for those who wish to work behind the scenes are also available.   Participation in the set-up and striking of the stage is mandatory in order to pass the class and will include time outside of the Wednesday afternoon schedule.  There are no fees charged to take this class."]]
    ] ; end content
   ))

(defn lps-extras-page [filename all-pages]
  (standard-page
   filename
   all-pages
   [:h3 "Testing"]
   [:div.first
    " "
    [:a {:href "#"} [:img {:src "images/model1.jpg", :alt ""}]]
    [:h1 "STUFF"]
    [:p
     "More Stuff."]
    ] ; end content
   ))

(defn contact-page [filename all-pages]
  (standard-page
   filename
   all-pages
   [:h3 "Contact"]
   [:div.first
    " "
    [:a {:href "#"} [:img {:src "images/model1.jpg", :alt ""}]]
    [:h1 "FACULTY"]
    [:p
     [:ul
      [:li "Headmaster: " headmaster]
      [:li "Elementary Principal: " elementary-principal]
      [:li "Admissions Director: " elementary-admissions-director]
      [:li "Secretary: " secretary]]][:br][:br][:br][:br]
    [:h1 "Contact Info"]
    [:h4 "Phone: " phone]
    [:h4 "Email: " email]
    [:h4 "Address: " address1 address2]
    [:p "Open on " [:a {:href "https://goo.gl/maps/3nGr3gXAYy52"} "Google Maps"]]
    ] ; end content
   ))

(defn under-construction-page [filename all-pages]
  (standard-page
   filename
   all-pages
   [:h3 "Under Construction"]
   [:div.first
    " "
    [:a {:href "#"} [:img {:src "images/model1.jpg", :alt ""}]]
    [:h1 "COMING SOON"]
    [:p
     "Please pardon our mess while we continue to migrate our site to the new platform.  New content will be coming shortly."]]
   ))

(defn test-page [filename all-pages]
  (standard-page
   filename
   all-pages
   [:h3 "Testing"]
   [:div.first
    " "
    [:a {:href "#"} [:img {:src "images/model1.jpg", :alt ""}]]
    [:h1 "STUFF"]
    [:p
     "More Stuff."]
    ] ; end content
   ))

;;; Here's where we actually define each link, in order. Each link has a title
;;; and a template page function.

(def all-pages [
                [home-page "Home" index-page]
                ["about.html" "About Us" about-page]
                ["admissions.html" "Admissions" admissions-page]
                ["elementary.html" "Elementary" elementary-page]
                ["secondary-school.html" "Secondary School" secondary-school-page]
                ["lps-extras.html" "LPS Extras" under-construction-page]
                ;["test.html" "Test Page" test-page]
                [last-page "Contact Us" contact-page]
                ])

(defmacro write-page
  "Our main page generation function."
  [filename template all-pages]
  `(spit (str "resources/" ~filename) (html5 (~template ~filename ~all-pages))))

(defn -main
  "This will create a html file for every page in all-pages."
  []
  (doseq [page all-pages]
    (write-page (first page) (nth page 2) all-pages)))

(-main)
