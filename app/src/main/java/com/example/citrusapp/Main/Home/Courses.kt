package com.example.citrusapp.Main.Home

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.citrusapp.R

data class Course(
    val title: String,
    val overview: String,
    val purpose: String,
    val learnings: String,
    val careers: List<String>,
    val subMajors: List<String> = emptyList()
)

@Composable
fun CEAScreen(navController: NavHostController) {
    val courses = listOf(
        Course(
            title = "Bachelor of Science in Civil Engineering",
            overview = "Civil Engineering at NwSSU equips students with the skills to design and build the world around us — from bridges and highways to sustainable water systems and modern infrastructures. With a strong foundation in mathematics, physics, and hands-on applications, students learn to solve real-world problems that impact society.",
            purpose = "The program aims to develop future-ready civil engineers who are not only technically skilled but also environmentally conscious and community-oriented. At NwSSU, we nurture professionals who are prepared to lead infrastructure development in both local and international contexts.",
            learnings = "Students will gain a deep understanding of structural analysis, construction methods, soil mechanics, hydrology, transportation systems, and project management. The curriculum includes practical lab work, industry simulations, and internship opportunities within government and private construction sectors.",
            careers = listOf(
                "Structural Engineer",
                "Project Manager",
                "Construction Supervisor",
                "Water Resource Specialist",
                "Geotechnical Engineer",
                "Transportation Engineer",
                "Estimator or Quantity Surveyor",
                "Government Infrastructure Officer",
                "Academic or Researcher in Civil Engineering",
                "Opportunities in international construction and consultancy firms"
            ),

            subMajors = listOf(
                "Major in Structural Engineering",
                "Major in Water Resources Engineering",
                "Major in Geotechnical Engineering"
            )
        )
        ,
        Course(
            title = "Bachelor of Science in Mechanical Engineering",
            overview = "The BS in Mechanical Engineering at Northwest Samar State University equips students with the foundational and advanced skills needed to design, analyze, and maintain mechanical systems—from engines and machines to manufacturing processes. Students develop a strong grasp of thermodynamics, materials science, and mechanical design using cutting-edge tools and equipment.",
            purpose = "This program aims to produce competent and innovative mechanical engineers who can contribute to regional and national development. With a strong emphasis on practical experience, critical thinking, and community-based projects, NSU’s Mechanical Engineering graduates are prepared to solve real-world problems and support the country’s growing industries.",
            learnings = "Students will gain proficiency in mechanical system design, CAD (Computer-Aided Design), fluid mechanics, heat transfer, robotics, and machine dynamics. The curriculum also includes exposure to automation, energy systems, and sustainable engineering practices, ensuring a well-rounded and forward-thinking education.",
            careers = listOf(
                "Plant or Manufacturing Engineer",
                "HVAC (Heating, Ventilation, and Air Conditioning) Engineer",
                "Automotive Engineer",
                "Design Engineer",
                "Energy and Power Systems Analyst",
                "Maintenance or Reliability Engineer",
                "Research and Development Specialist",
                "Project Manager in Engineering Firms",
                "Government Infrastructure and Utility Sector Positions",
                "Entrepreneur in Mechanical Design or Fabrication"
            )
        )
        ,
        Course(
            title = "Bachelor of Science in Electrical Engineering",
            overview = "Electrical Engineering is a broad field that involves the study and application of electricity, electronics, and electromagnetism. This program prepares students to design, develop, and maintain electrical systems used in power generation, telecommunications, and electronics.",
            purpose = "The purpose of this course is to equip students with strong analytical, mathematical, and problem-solving skills necessary to design and implement electrical systems and technologies that improve quality of life and support industrial development.",
            learnings = "Students will learn about circuit theory, control systems, electronics, power systems, renewable energy, and instrumentation. They will also gain hands-on experience with electrical design tools, simulation software, and laboratory testing.",
            careers = listOf(
                "Electrical Engineer",
                "Power Systems Engineer",
                "Control Systems Engineer",
                "Electronics Design Engineer",
                "Telecommunications Engineer",
                "Instrumentation Engineer",
                "Renewable Energy Consultant",
                "Project Engineer"
            )
        )
        ,
        Course(
            title = "Bachelor of Science in Electronics Engineering",
            overview = "Electronics Engineering focuses on the design, development, and application of electronic devices, circuits, and systems used in everything from consumer gadgets and medical equipment to telecommunications and industrial automation.",
            purpose = "This program aims to develop engineers who can innovate and optimize electronic and communication technologies, ensuring reliable, efficient, and sustainable solutions that meet evolving societal and industrial needs.",
            learnings = "Students gain strong foundations in semiconductor physics, analog and digital circuit design, embedded systems, signal processing, telecommunications, instrumentation, and automation. Laboratory work and design projects build proficiency with simulation tools, PCB design, microcontroller programming, and testing equipment.",
            careers = listOf(
                "Electronics Design Engineer",
                "Embedded Systems Engineer",
                "Telecommunications Engineer",
                "Signal Processing Engineer",
                "Instrumentation & Control Engineer",
                "RF/Microwave Engineer",
                "Hardware Test Engineer",
                "IoT Solutions Developer",
                "Automation Engineer",
                "Technical Sales Engineer"
            )
        )
        ,
        Course(
            title = "Bachelor of Science in Architecture",
            overview = "Architecture is the art and science of designing buildings and physical structures that are both functional and aesthetically pleasing. This program combines creativity, technical knowledge, and sustainability principles to shape spaces that influence how people live and interact.",
            purpose = "The purpose of this course is to train future architects in the responsible design of built environments that respond to cultural, environmental, social, and technological contexts. It aims to balance artistic expression with practical construction techniques and regulations.",
            learnings = "Students will study architectural design, history and theory, building technology, construction methods, structural systems, environmental design, urban planning, and computer-aided design (CAD). Design studios and real-world projects enhance their creativity and critical thinking.",
            careers = listOf(
                "Licensed Architect",
                "Urban Planner",
                "Interior Designer",
                "Landscape Architect",
                "Sustainable Design Consultant",
                "Construction Project Manager",
                "Architectural Renderer/Visualizer",
                "Heritage Conservation Specialist",
                "Building Information Modeling (BIM) Specialist",
                "Set Designer for Film/TV"
            )
        ),
        Course(
            title = "Bachelor of Science in Computer Engineering",
            overview = "Computer Engineering is a multidisciplinary field that combines principles of electrical engineering and computer science to design, develop, and optimize computer systems, hardware, and software integration. It covers everything from microprocessors to embedded systems and high-level programming.",
            purpose = "The purpose of this course is to prepare students to become competent professionals capable of innovating and building hardware-software solutions for real-world problems in industries such as computing, telecommunications, robotics, and automation.",
            learnings = "Students will learn computer architecture, embedded systems, digital logic design, operating systems, networking, programming, data structures, algorithms, and hardware-software integration. They’ll gain hands-on experience through labs, capstone projects, and internships.",
            careers = listOf(
                "Computer Engineer",
                "Embedded Systems Developer",
                "Hardware Design Engineer",
                "Firmware Developer",
                "Systems Engineer",
                "Network Engineer",
                "IoT Solutions Developer",
                "Robotics Engineer",
                "Cybersecurity Specialist",
                "Software Engineer"
            )
        )
    )

    val expandedStates = remember { mutableStateMapOf<Int, Boolean>() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Image(
            painter = painterResource(id = R.drawable.shapes),
            contentDescription = "Background shapes",
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.cealogo),
                    contentDescription = "College of Engineering and Architecture Logo",
                    modifier = Modifier.size(64.dp)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = "College of Engineering and Architecture",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Available Programs",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            HorizontalDivider(
                modifier = Modifier.padding(bottom = 12.dp),
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                itemsIndexed(courses) { index, course ->
                    val isExpanded = expandedStates[index] == true

                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        tonalElevation = 2.dp,
                        color = MaterialTheme.colorScheme.surface,
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize()
                    ) {
                        Column(
                            modifier = Modifier
                                .clickable { expandedStates[index] = !isExpanded }
                                .padding(16.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = course.title,
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.weight(1f)
                                )

                                Icon(
                                    painter = painterResource(
                                        id = if (isExpanded)
                                            R.drawable.ic_arrow_up
                                        else
                                            R.drawable.ic_arrow_down
                                    ),
                                    contentDescription = if (isExpanded) "Collapse" else "Expand",
                                    tint = MaterialTheme.colorScheme.onSurface
                                )
                            }

                            if (isExpanded) {
                                Spacer(modifier = Modifier.height(12.dp))

                                HorizontalDivider(
                                    thickness = 1.dp,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                                )

                                Spacer(modifier = Modifier.height(12.dp))

                                if (course.subMajors.isNotEmpty()) {
                                    Text(
                                        text = "Majors/Specializations",
                                        style = MaterialTheme.typography.titleSmall,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))

                                    Column {
                                        course.subMajors.forEach { major ->
                                            Text(
                                                text = "• $major",
                                                style = MaterialTheme.typography.bodySmall,
                                                modifier = Modifier.padding(start = 8.dp, bottom = 4.dp)
                                            )
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(12.dp))
                                }

                                Column {
                                    Text(
                                        text = "Overview",
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.titleSmall,
                                        modifier = Modifier.padding(bottom = 4.dp)
                                    )
                                    Text(
                                        text = course.overview,
                                        fontSize = 14.sp,
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier.padding(bottom = 12.dp)
                                    )

                                    HorizontalDivider(
                                        thickness = 1.dp,
                                        modifier = Modifier.padding(vertical = 12.dp),
                                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                                    )

                                    Text(
                                        text = "Purpose",
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.titleSmall,
                                        modifier = Modifier.padding(bottom = 4.dp)
                                    )
                                    Text(
                                        text = course.purpose,
                                        fontSize = 14.sp,
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier.padding(bottom = 12.dp)
                                    )

                                    HorizontalDivider(
                                        thickness = 1.dp,
                                        modifier = Modifier.padding(vertical = 12.dp),
                                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                                    )

                                    Text(
                                        text = "What will you learn?",
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.titleSmall,
                                        modifier = Modifier.padding(bottom = 4.dp)
                                    )
                                    Text(
                                        text = course.learnings,
                                        fontSize = 14.sp,
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier.padding(bottom = 12.dp)
                                    )

                                    HorizontalDivider(
                                        thickness = 1.dp,
                                        modifier = Modifier.padding(vertical = 12.dp),
                                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                                    )

                                    Text(
                                        text = "Possible Career Opportunities",
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.titleSmall,
                                        modifier = Modifier.padding(top = 12.dp, bottom = 4.dp),
                                    )

                                    course.careers.forEach { career ->
                                        Text(
                                            text = "• $career",
                                            fontSize = 14.sp,
                                            style = MaterialTheme.typography.bodySmall,
                                            modifier = Modifier.padding(start = 8.dp, bottom = 4.dp)
                                        )
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun EducScreen(navController: NavHostController) {
    val courses = listOf(
        Course(
            title = "Bachelor of Elementary Education",
            overview = "Northwest Samar State University (NwSSU) offers a dynamic Bachelor of Elementary Education program designed to prepare competent and compassionate educators. With experienced faculty, modern facilities, and a supportive learning environment, NwSSU ensures students gain both theoretical knowledge and practical skills essential for shaping the minds of young learners.",
            purpose = "The program’s goal at NwSSU is to develop highly qualified, community-oriented elementary teachers who are equipped with innovative teaching methods, digital literacy, and strong ethical values. Graduates are empowered to become catalysts for positive change in the education sector locally and beyond.",
            learnings = "Students at NwSSU engage in comprehensive coursework covering educational psychology, curriculum development, inclusive teaching strategies, classroom management, and assessment techniques. The program emphasizes hands-on experience through supervised practicum and community immersion activities, ensuring readiness to handle diverse classroom challenges.",
            careers = listOf(
                "Licensed Elementary Teacher ready to serve in public and private schools",
                "Curriculum and Instruction Specialist in education centers",
                "Academic Coordinator in local schools and learning institutions",
                "Special Education (SPED) Teacher addressing diverse learner needs",
                "Community Education Advocate supporting local development programs",
                "Education Program Officer for government and non-government organizations",
                "Learning Resource Developer and Educational Content Creator",
                "Future school leaders with potential for administrative roles",
                "Opportunities for graduate studies and research at NwSSU",
                "Active participant in nation-building through quality education"
            ),


        )
        ,
        Course(
            title = "Bachelor of Secondary Education",
            overview = "Northwest Samar State University offers a comprehensive Bachelor of Secondary Education program designed to prepare skilled and passionate teachers for the high school level. With a focus on both content mastery and effective pedagogy, NwSSU equips students to become inspiring educators who can shape the minds of the youth in various subject areas.",
            purpose = "The program aims to develop future secondary educators who are academically competent, socially responsible, and technologically adept. NwSSU nurtures students to become lifelong learners and advocates of quality education, ready to meet the challenges of the evolving educational landscape.",
            learnings = "Students will develop expertise in their chosen specialization (such as Mathematics, Science, English, Social Studies, or Technology and Livelihood Education), alongside courses in teaching methods, educational psychology, curriculum development, classroom management, and assessment. Extensive practicum and community engagement activities ensure graduates are prepared for real-world classroom environments.",
            careers = listOf(
                "Licensed Secondary School Teacher in public and private institutions",
                "Curriculum Developer for secondary education programs",
                "Academic Coordinator or Department Head",
                "Education Program Specialist in government and NGOs",
                "Specialized Subject Teacher (e.g., Math, Science, English, TLE)",
                "School Administrator with further graduate studies",
                "Education Researcher and Policy Analyst",
                "Trainer or Facilitator in teacher training centers",
                "Content Developer for educational publishing houses",
                "Advocate for youth development and community outreach programs"
            ),
            subMajors = listOf(
                "Major in Mathematics",
                "Major in English",
                "Major in Science",
                "Major in Social Studies",
                "Major in Filipino",
                "Major in Values Education",
            )
        )
        ,
        Course(
            title = "Bachelor of Physical Education",
            overview = "Northwest Samar State University’s Bachelor of Physical Education program develops highly skilled and motivated professionals in the field of sports, fitness, and wellness. The program emphasizes the science of human movement, health promotion, and coaching techniques to prepare graduates for diverse roles in physical education and sports management.",
            purpose = "The program aims to cultivate physically competent and socially responsible individuals who promote active lifestyles, wellness, and sportsmanship. Graduates from NwSSU are equipped to lead community-based health initiatives, coach athletes, and contribute to the development of physical education in schools and organizations.",
            learnings = "Students will gain knowledge in exercise physiology, kinesiology, biomechanics, sports psychology, health education, coaching methodologies, and first aid. Practical training through sports clinics, fitness assessments, and internship programs ensures readiness to apply theory to real-world physical education settings.",
            careers = listOf(
                "Physical Education Teacher in schools and colleges",
                "Sports Coach or Trainer",
                "Fitness and Wellness Coach",
                "Athletic Trainer",
                "Recreational Therapist",
                "Health and Wellness Program Coordinator",
                "Sports Official or Referee",
                "Community Sports Development Officer",
                "Sports Science Researcher",
                "Entrepreneur in Fitness and Sports Management"
            )
        ),
        Course(
            title = "Bachelor of Technical-Vocational Teacher Education",
            overview = "Northwest Samar State University’s Bachelor of Technical-Vocational Teacher Education program prepares skilled educators who specialize in teaching technical and vocational subjects. The program combines pedagogical theory with hands-on technical training to equip future teachers to train students in practical and industry-relevant skills.",
            purpose = "The program aims to develop competent technical-vocational educators who can effectively deliver competency-based training in various technical fields. NwSSU focuses on producing teachers who can empower students to become productive members of the workforce, contributing to local and national economic development.",
            learnings = "Students gain expertise in both education and their chosen technical specialization such as automotive technology, electronics, welding, culinary arts, or information and communication technology. The curriculum covers teaching methodologies, curriculum development, assessment, plus practical workshops and on-the-job training to ensure readiness for real-world teaching environments.",
            careers = listOf(
                "Technical-Vocational Teacher in TESDA-accredited schools",
                "Vocational Training Instructor",
                "Curriculum Developer for technical education",
                "Skills Assessment Specialist",
                "Technical Program Coordinator",
                "Industry Liaison Officer for technical education",
                "Entrepreneur in technical training services",
                "Workforce Development Officer",
                "Technical Education Consultant",
                "Trainer or Facilitator in government and private training centers"
            )
        ),
        Course(
            title = "Bachelor of Technology and Livelihood Education",
            overview = "Northwest Samar State University’s Bachelor of Technology and Livelihood Education program prepares future educators to teach practical and livelihood skills essential for community development and self-sufficiency. The program integrates technical knowledge with effective teaching strategies to train competent and innovative teachers in various livelihood areas.",
            purpose = "This program aims to equip students with the skills to become proficient teachers of technical-vocational and livelihood subjects, promoting sustainable livelihood and entrepreneurship in local communities. Graduates are prepared to inspire learners to apply practical skills that improve their quality of life and contribute to economic growth.",
            learnings = "Students will develop expertise in diverse livelihood areas such as agriculture, home economics, entrepreneurship, ICT, and industrial arts, along with pedagogy, curriculum development, classroom management, and assessment. Hands-on training, community immersion, and teaching practicum are integral to preparing graduates for effective classroom and community engagement.",
            careers = listOf(
                "Technical-Vocational Teacher in schools and training centers",
                "Livelihood Skills Trainer in community development programs",
                "Curriculum Developer for livelihood education",
                "Entrepreneurship Educator",
                "Agricultural Extension Worker",
                "Home Economics Specialist",
                "ICT Trainer",
                "Community Program Coordinator",
                "Skills Development Officer",
                "Education Consultant for livelihood and vocational training"
            )
        )
    )

    val expandedStates = remember { mutableStateMapOf<Int, Boolean>() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Image(
            painter = painterResource(id = R.drawable.shapes),
            contentDescription = "Background shapes",
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.educlogo),
                    contentDescription = "College of Education Logo",
                    modifier = Modifier.size(64.dp)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = "College of Education",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Available Programs",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            HorizontalDivider(
                modifier = Modifier.padding(bottom = 12.dp),
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                itemsIndexed(courses) { index, course ->
                    val isExpanded = expandedStates[index] == true

                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        tonalElevation = 2.dp,
                        color = MaterialTheme.colorScheme.surface,
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize()
                    ) {
                        Column(
                            modifier = Modifier
                                .clickable { expandedStates[index] = !isExpanded }
                                .padding(16.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = course.title,
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.weight(1f)
                                )

                                Icon(
                                    painter = painterResource(
                                        id = if (isExpanded)
                                            R.drawable.ic_arrow_up
                                        else
                                            R.drawable.ic_arrow_down
                                    ),
                                    contentDescription = if (isExpanded) "Collapse" else "Expand",
                                    tint = MaterialTheme.colorScheme.onSurface
                                )
                            }

                            if (isExpanded) {
                                Spacer(modifier = Modifier.height(12.dp))

                                HorizontalDivider(
                                    thickness = 1.dp,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                                )

                                Spacer(modifier = Modifier.height(12.dp))

                                if (course.subMajors.isNotEmpty()) {
                                    Text(
                                        text = "Majors/Specializations",
                                        style = MaterialTheme.typography.titleSmall,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))

                                    Column {
                                        course.subMajors.forEach { major ->
                                            Text(
                                                text = "• $major",
                                                style = MaterialTheme.typography.bodySmall,
                                                modifier = Modifier.padding(start = 8.dp, bottom = 4.dp)
                                            )
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(12.dp))
                                }

                                Column {
                                    Text(
                                        text = "Overview",
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.titleSmall,
                                        modifier = Modifier.padding(bottom = 4.dp)
                                    )
                                    Text(
                                        text = course.overview,
                                        fontSize = 14.sp,
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier.padding(bottom = 12.dp)
                                    )

                                    HorizontalDivider(
                                        thickness = 1.dp,
                                        modifier = Modifier.padding(vertical = 12.dp),
                                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                                    )

                                    Text(
                                        text = "Purpose",
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.titleSmall,
                                        modifier = Modifier.padding(bottom = 4.dp)
                                    )
                                    Text(
                                        text = course.purpose,
                                        fontSize = 14.sp,
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier.padding(bottom = 12.dp)
                                    )

                                    HorizontalDivider(
                                        thickness = 1.dp,
                                        modifier = Modifier.padding(vertical = 12.dp),
                                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                                    )

                                    Text(
                                        text = "What will you learn?",
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.titleSmall,
                                        modifier = Modifier.padding(bottom = 4.dp)
                                    )
                                    Text(
                                        text = course.learnings,
                                        fontSize = 14.sp,
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier.padding(bottom = 12.dp)
                                    )

                                    HorizontalDivider(
                                        thickness = 1.dp,
                                        modifier = Modifier.padding(vertical = 12.dp),
                                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                                    )

                                    Text(
                                        text = "Possible Career Opportunities",
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.titleSmall,
                                        modifier = Modifier.padding(top = 12.dp, bottom = 4.dp),
                                    )

                                    course.careers.forEach { career ->
                                        Text(
                                            text = "• $career",
                                            fontSize = 14.sp,
                                            style = MaterialTheme.typography.bodySmall,
                                            modifier = Modifier.padding(start = 8.dp, bottom = 4.dp)
                                        )
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ManagementScreen(navController: NavHostController) {
    val courses = listOf(
        Course(
            title = "Bachelor of Science in Entrepreneurship",
            overview = "Northwest Samar State University (NwSSU) offers a vibrant Bachelor of Science in Entrepreneurship program designed to cultivate innovative and resourceful business leaders. Through expert faculty, entrepreneurial partnerships, and a supportive learning environment, NwSSU equips students with both theoretical foundations and practical skills to succeed in dynamic business landscapes.",
            purpose = "This program aims to develop future entrepreneurs who are not only business-savvy but also socially responsible and community-driven. At NwSSU, students learn to identify opportunities, create sustainable ventures, and contribute to the economic growth of the region and the country.",
            learnings = "Students will engage in comprehensive coursework including business planning, marketing strategies, financial management, innovation and creativity, business ethics, and digital entrepreneurship. Hands-on projects, start-up incubation support, and industry immersion prepare graduates to launch and manage successful enterprises.",
            careers = listOf(
                "Start-up Founder or Small Business Owner",
                "Business Development Manager",
                "Marketing and Sales Entrepreneur",
                "Social Entrepreneur focused on community impact",
                "Financial Analyst for new ventures",
                "Business Consultant or Coach",
                "Product or Brand Manager",
                "E-commerce Specialist",
                "Innovation Manager in corporations or government agencies",
                "Entrepreneurship Educator or Trainer"
            )
            ),
        Course(
            title = "Bachelor of Science in Office Administration",
            overview = "Northwest Samar State University offers a robust Bachelor of Science in Office Administration program that prepares students to excel in managing and supporting business operations. With a curriculum blending theory and practical skills, NwSSU equips students with the competencies necessary for efficient office management and administrative leadership in diverse industries.",
            purpose = "The program aims to develop highly skilled office administrators who are organized, tech-savvy, and capable of handling complex administrative tasks. NwSSU focuses on nurturing professionals who contribute to business productivity and effective workplace communication, supporting both local and regional enterprises.",
            learnings = "Students will acquire knowledge in office management, business communication, records management, human resource administration, financial procedures, and office technology applications. Practical training through internships and real-world projects ensures graduates are workplace-ready and able to adapt to evolving business environments.",
            careers = listOf(
                "Office Manager or Administrator",
                "Executive Assistant or Personal Secretary",
                "Human Resources Assistant",
                "Records and Information Manager",
                "Administrative Services Manager",
                "Customer Service Supervisor",
                "Project Coordinator or Office Support Specialist",
                "Payroll or Benefits Administrator",
                "Business Process Analyst",
                "Entrepreneur in Administrative Services"
            )
        )

        ,
        Course(
            title = "Bachelor of Science in Hospitality Management",
            overview = "Northwest Samar State University’s Bachelor of Science in Hospitality Management program prepares students to become skilled professionals in the vibrant hospitality and tourism industry. The program combines practical training and theoretical knowledge to develop expertise in hotel, restaurant, and event management, ensuring graduates are ready to meet global standards.",
            purpose = "This program aims to produce innovative and customer-focused hospitality managers who contribute to the growth of local and international tourism. NwSSU nurtures graduates with strong leadership, service excellence, and business acumen to excel in dynamic and competitive hospitality environments.",
            learnings = "Students gain comprehensive knowledge in hospitality operations, food and beverage management, tourism marketing, event planning, financial management, and customer service. Hands-on experiences through internships, industry partnerships, and real-world projects equip students with practical skills and professional confidence.",
            careers = listOf(
                "Hotel or Resort Manager",
                "Food and Beverage Manager",
                "Event Planner or Coordinator",
                "Tourism Officer or Travel Consultant",
                "Guest Relations Manager",
                "Hospitality Marketing Specialist",
                "Casino or Cruise Ship Staff",
                "Restaurant Manager",
                "Hotel Front Office Manager",
                "Entrepreneur in Hospitality and Tourism"
            )
        ),
        Course(
            title = "Bachelor of Science in Business Administration",
            overview = "Northwest Samar State University’s Bachelor of Science in Business Administration program offers a comprehensive curriculum designed to develop future business leaders and entrepreneurs. With a strong focus on practical skills and ethical business practices, NwSSU equips students with the knowledge needed to succeed in the dynamic world of commerce and management.",
            purpose = "The program aims to produce competent, innovative, and socially responsible business professionals who can drive economic growth locally and globally. NwSSU emphasizes leadership, strategic thinking, and effective decision-making to prepare graduates for diverse roles in the business sector.",
            learnings = "Students acquire expertise in core business areas such as marketing, finance, human resource management, operations, and organizational behavior. Through case studies, internships, and real-world projects, students develop critical thinking, communication, and problem-solving skills essential for thriving in competitive business environments.",
            careers = listOf(
                "Business Manager or Administrator",
                "Marketing Specialist or Sales Manager",
                "Financial Analyst or Accountant",
                "Human Resources Manager",
                "Operations Manager",
                "Entrepreneur or Small Business Owner",
                "Business Consultant",
                "Customer Relationship Manager",
                "Project Manager",
                "Corporate Trainer or Business Development Officer"
            ),
            subMajors = listOf(
                "Major in Marketing Management"
            )
        ),
        Course(
            title = "Bachelor of Science in Tourism Management",
            overview = "Northwest Samar State University’s Bachelor of Science in Tourism Management program equips students with the knowledge and skills to thrive in the growing tourism and hospitality industry. The program blends theoretical foundations with practical experience, preparing graduates to promote sustainable tourism and cultural heritage in the region and beyond.",
            purpose = "This program aims to develop competent, innovative, and socially responsible tourism professionals who can enhance local tourism development and global competitiveness. NwSSU fosters leadership, customer service excellence, and environmental awareness among students to support the sustainable growth of the tourism sector.",
            learnings = "Students gain expertise in tourism planning, hospitality management, cultural heritage preservation, travel agency operations, event management, and sustainable tourism practices. The curriculum includes field trips, internships, and community engagement to provide hands-on learning and real-world exposure.",
            careers = listOf(
                "Tourism Development Officer",
                "Travel and Tour Operations Manager",
                "Event and Conference Planner",
                "Hotel and Resort Manager",
                "Cultural Heritage Manager",
                "Eco-tourism Specialist",
                "Tour Guide and Customer Service Manager",
                "Marketing and Promotions Coordinator",
                "Sustainable Tourism Consultant",
                "Entrepreneur in Tourism and Hospitality"
            )
        )
    )

    val expandedStates = remember { mutableStateMapOf<Int, Boolean>() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Image(
            painter = painterResource(id = R.drawable.shapes),
            contentDescription = "Background shapes",
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.managementlogo),
                    contentDescription = "College of Management Logo",
                    modifier = Modifier.size(64.dp)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = "College of Management",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Available Programs",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            HorizontalDivider(
                modifier = Modifier.padding(bottom = 12.dp),
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                itemsIndexed(courses) { index, course ->
                    val isExpanded = expandedStates[index] == true

                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        tonalElevation = 2.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize()
                    ) {
                        Column(
                            modifier = Modifier
                                .clickable { expandedStates[index] = !isExpanded }
                                .padding(16.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = course.title,
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.weight(1f)
                                )

                                Icon(
                                    painter = painterResource(
                                        id = if (isExpanded)
                                            R.drawable.ic_arrow_up
                                        else
                                            R.drawable.ic_arrow_down
                                    ),
                                    contentDescription = if (isExpanded) "Collapse" else "Expand",
                                    tint = MaterialTheme.colorScheme.onSurface
                                )
                            }

                            if (isExpanded) {
                                Spacer(modifier = Modifier.height(12.dp))

                                HorizontalDivider(
                                    thickness = 1.dp,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                                )

                                Spacer(modifier = Modifier.height(12.dp))

                                if (course.subMajors.isNotEmpty()) {
                                    Text(
                                        text = "Majors/Specializations",
                                        style = MaterialTheme.typography.titleSmall,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))

                                    Column {
                                        course.subMajors.forEach { major ->
                                            Text(
                                                text = "• $major",
                                                style = MaterialTheme.typography.bodySmall,
                                                modifier = Modifier.padding(start = 8.dp, bottom = 4.dp)
                                            )
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(12.dp))
                                }

                                Column {
                                    Text(
                                        text = "Overview",
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.titleSmall,
                                        modifier = Modifier.padding(bottom = 4.dp)
                                    )
                                    Text(
                                        text = course.overview,
                                        fontSize = 14.sp,
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier.padding(bottom = 12.dp)
                                    )

                                    HorizontalDivider(
                                        thickness = 1.dp,
                                        modifier = Modifier.padding(vertical = 12.dp),
                                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                                    )

                                    Text(
                                        text = "Purpose",
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.titleSmall,
                                        modifier = Modifier.padding(bottom = 4.dp)
                                    )
                                    Text(
                                        text = course.purpose,
                                        fontSize = 14.sp,
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier.padding(bottom = 12.dp)
                                    )

                                    HorizontalDivider(
                                        thickness = 1.dp,
                                        modifier = Modifier.padding(vertical = 12.dp),
                                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                                    )

                                    Text(
                                        text = "What will you learn?",
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.titleSmall,
                                        modifier = Modifier.padding(bottom = 4.dp)
                                    )
                                    Text(
                                        text = course.learnings,
                                        fontSize = 14.sp,
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier.padding(bottom = 12.dp)
                                    )

                                    HorizontalDivider(
                                        thickness = 1.dp,
                                        modifier = Modifier.padding(vertical = 12.dp),
                                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                                    )

                                    Text(
                                        text = "Possible Career Opportunities",
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.titleSmall,
                                        modifier = Modifier.padding(top = 12.dp, bottom = 4.dp),
                                    )

                                    course.careers.forEach { career ->
                                        Text(
                                            text = "• $career",
                                            fontSize = 14.sp,
                                            style = MaterialTheme.typography.bodySmall,
                                            modifier = Modifier.padding(start = 8.dp, bottom = 4.dp)
                                        )
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CCISScreen(navController: NavHostController) {
    val courses = listOf(
        Course(
            title = "Bachelor of Science in Entertainment and Multimedia Computing",
            overview = "Northwest Samar State University’s Bachelor of Science in Entertainment and Multimedia Computing prepares students to become creative technologists in the fast-evolving digital media and entertainment industry. The program integrates computing, design, and multimedia production, offering a strong foundation in both technical skills and artistic expression.",
            purpose = "This program aims to develop skilled professionals who can innovate in areas such as game development, animation, digital storytelling, and interactive media. At NwSSU, students are nurtured to combine creativity with technical expertise to meet the demands of local and global entertainment markets.",
            learnings = "Students will study computer graphics, animation, game design and programming, visual effects, multimedia system development, sound engineering, and user experience design. Hands-on labs, collaborative projects, and internships ensure practical experience and industry readiness.",
            careers = listOf(
                "Game Developer or Designer",
                "Multimedia Programmer",
                "3D Animator and Visual Effects Artist",
                "Interactive Media Developer",
                "Sound and Audio Engineer",
                "Digital Content Creator",
                "User Experience (UX) Designer",
                "Film and Television Visual Effects Specialist",
                "Mobile and Web Application Developer",
                "Creative Technologist or Media Producer"
            )
        ),
        Course(
            title = "Bachelor of Science in Information Systems",
            overview = "Northwest Samar State University’s Bachelor of Science in Information Systems program delivers a cutting-edge education that blends business acumen with advanced technology skills. NwSSU equips students to design, implement, and manage information systems that drive organizational success and innovation across industries.",
            purpose = "This program aims to develop versatile professionals who can analyze business needs and apply technology solutions effectively. NwSSU focuses on producing graduates who are not only tech-savvy but also strategic thinkers ready to lead digital transformation efforts locally, regionally, and beyond.",
            learnings = "Students gain expertise in database management, systems analysis and design, programming, cybersecurity, networking, enterprise resource planning (ERP), and project management. Real-world case studies, internships, and collaborative projects ensure graduates are industry-ready and highly competitive.",
            careers = listOf(
                "Systems Analyst",
                "Business Analyst",
                "Information Systems Manager",
                "Database Administrator",
                "IT Project Manager",
                "Cybersecurity Specialist",
                "Network Administrator",
                "ERP Consultant",
                "Data Analyst",
                "IT Consultant or Entrepreneur"
            )
        )

        ,
        Course(
            title = "Bachelor of Science in Computer Science",
            overview = "Northwest Samar State University’s Bachelor of Science in Computer Science program is designed to produce highly skilled and innovative professionals ready to excel in today’s fast-evolving tech industry. The program combines rigorous theoretical foundations with practical application in software development, algorithms, and computing systems.",
            purpose = "The program aims to develop competent computer scientists who can solve complex problems, drive technological innovation, and contribute to regional and national progress. NwSSU nurtures critical thinkers and creative coders equipped to meet the demands of the digital age in various sectors.",
            learnings = "Students will master programming languages, data structures, algorithms, software engineering, database systems, artificial intelligence, and cybersecurity. Through hands-on projects, internships, and collaborative research, graduates gain real-world experience and a competitive edge in the IT field.",
            careers = listOf(
                "Software Developer or Engineer",
                "Systems Analyst",
                "Cybersecurity Specialist",
                "Data Scientist or Analyst",
                "Network Administrator",
                "AI/Machine Learning Engineer",
                "Database Administrator",
                "Mobile or Web Application Developer",
                "IT Consultant",
                "Entrepreneur in Technology"
            )
        ),
        Course(
            title = "Bachelor of Science in Information Technology",
            overview = "Northwest Samar State University’s Bachelor of Science in Information Technology program equips students with the knowledge and skills to design, implement, and manage computer-based information systems. The program integrates theoretical foundations with hands-on training in software development, networking, database management, and cybersecurity.",
            purpose = "The program aims to develop competent IT professionals capable of addressing complex technological challenges and delivering innovative solutions that support organizational goals. Graduates are prepared to contribute to the digital transformation of industries and communities locally and globally.",
            learnings = "Students will study programming, system analysis and design, database administration, network security, web technologies, cloud computing, and IT project management. Practical experiences through internships and capstone projects ensure graduates are ready for real-world IT environments.",
            careers = listOf(
                "Network Administrator",
                "Systems Analyst",
                "Database Administrator",
                "Cybersecurity Analyst",
                "Software Developer",
                "IT Support Specialist",
                "Mobile or Web Application Developer",
                "Cloud Solutions Architect",
                "IT Project Manager",
                "Technology Consultant"
            )
        )
    )

    val expandedStates = remember { mutableStateMapOf<Int, Boolean>() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Image(
            painter = painterResource(id = R.drawable.shapes),
            contentDescription = "Background shapes",
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.ccislogo),
                    contentDescription = "College of Computing and Information Sciences Logo",
                    modifier = Modifier.size(64.dp)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = "College of Computing and Information Sciences",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Available Programs",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            HorizontalDivider(
                modifier = Modifier.padding(bottom = 12.dp),
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                itemsIndexed(courses) { index, course ->
                    val isExpanded = expandedStates[index] == true

                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        tonalElevation = 2.dp,
                        color = MaterialTheme.colorScheme.surface,
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize()
                    ) {
                        Column(
                            modifier = Modifier
                                .clickable { expandedStates[index] = !isExpanded }
                                .padding(16.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = course.title,
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.weight(1f)
                                )

                                Icon(
                                    painter = painterResource(
                                        id = if (isExpanded)
                                            R.drawable.ic_arrow_up
                                        else
                                            R.drawable.ic_arrow_down
                                    ),
                                    contentDescription = if (isExpanded) "Collapse" else "Expand",
                                    tint = MaterialTheme.colorScheme.onSurface
                                )
                            }

                            if (isExpanded) {
                                Spacer(modifier = Modifier.height(12.dp))

                                HorizontalDivider(
                                    thickness = 1.dp,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                                )

                                Spacer(modifier = Modifier.height(12.dp))

                                if (course.subMajors.isNotEmpty()) {
                                    Text(
                                        text = "Majors/Specializations",
                                        style = MaterialTheme.typography.titleSmall,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))

                                    Column {
                                        course.subMajors.forEach { major ->
                                            Text(
                                                text = "• $major",
                                                style = MaterialTheme.typography.bodySmall,
                                                modifier = Modifier.padding(start = 8.dp, bottom = 4.dp)
                                            )
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(12.dp))
                                }

                                Column {
                                    Text(
                                        text = "Overview",
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.titleSmall,
                                        modifier = Modifier.padding(bottom = 4.dp)
                                    )
                                    Text(
                                        text = course.overview,
                                        fontSize = 14.sp,
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier.padding(bottom = 12.dp)
                                    )

                                    HorizontalDivider(
                                        thickness = 1.dp,
                                        modifier = Modifier.padding(vertical = 12.dp),
                                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                                    )

                                    Text(
                                        text = "Purpose",
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.titleSmall,
                                        modifier = Modifier.padding(bottom = 4.dp)
                                    )
                                    Text(
                                        text = course.purpose,
                                        fontSize = 14.sp,
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier.padding(bottom = 12.dp)
                                    )

                                    HorizontalDivider(
                                        thickness = 1.dp,
                                        modifier = Modifier.padding(vertical = 12.dp),
                                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                                    )

                                    Text(
                                        text = "What will you learn?",
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.titleSmall,
                                        modifier = Modifier.padding(bottom = 4.dp)
                                    )
                                    Text(
                                        text = course.learnings,
                                        fontSize = 14.sp,
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier.padding(bottom = 12.dp)
                                    )

                                    HorizontalDivider(
                                        thickness = 1.dp,
                                        modifier = Modifier.padding(vertical = 12.dp),
                                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                                    )

                                    Text(
                                        text = "Possible Career Opportunities",
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.titleSmall,
                                        modifier = Modifier.padding(top = 12.dp, bottom = 4.dp),
                                    )

                                    course.careers.forEach { career ->
                                        Text(
                                            text = "• $career",
                                            fontSize = 14.sp,
                                            style = MaterialTheme.typography.bodySmall,
                                            modifier = Modifier.padding(start = 8.dp, bottom = 4.dp)
                                        )
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CriminologyScreen(navController: NavHostController) {
    val courses = listOf(
        Course(
            title = "Bachelor of Science in Development Communication",
            overview = "Northwest Samar State University (NwSSU) offers a dynamic Bachelor of Science in Development Communication program designed to prepare students to use communication as a tool for social change and community development. The program combines theory and practice to equip students with skills in media, advocacy, and participatory communication.",
            purpose = "This program aims to develop communication professionals who can effectively engage communities, influence public policy, and promote sustainable development. Graduates will be prepared to work with diverse stakeholders to address social issues and empower marginalized groups through strategic communication.",
            learnings = "Students will study communication theories, media production, public relations, community organizing, development journalism, social marketing, and digital media. Practical work includes field projects, internships, and campaigns focused on real community challenges.",
            careers = listOf(
                "Development Communication Specialist",
                "Community Relations Officer",
                "Public Information Officer",
                "Media and Communications Officer for NGOs",
                "Advocacy and Campaign Manager",
                "Social Media Strategist for Development Projects",
                "Researcher in Communication and Social Change",
                "Broadcast Journalist with a focus on development issues",
                "Policy Communication Analyst",
                "Communication Consultant for Government and International Agencies"
            )
        ),
        Course(
            title = "Bachelor of Science in Community Development",
            overview = "Northwest Samar State University offers a comprehensive Bachelor of Science in Community Development program designed to equip students with the knowledge and skills to empower communities towards sustainable growth and social progress. The program emphasizes participatory approaches, social research, and development planning.",
            purpose = "The program aims to develop socially responsible professionals who can facilitate community engagement, design development programs, and implement projects that address local needs. Graduates are prepared to become catalysts for positive change in rural and urban communities.",
            learnings = "Students will study community organizing, social work principles, project management, local governance, environmental sustainability, and participatory research methods. Fieldwork, community immersion, and internship programs provide hands-on experience in real community settings.",
            careers = listOf(
                "Community Development Officer",
                "Program Coordinator for NGOs and Government Agencies",
                "Social Worker or Community Organizer",
                "Local Government Development Planner",
                "Project Manager in Social Development Projects",
                "Researcher in Social and Community Studies",
                "Environmental and Sustainability Advocate",
                "Youth and Women’s Development Facilitator",
                "Policy Analyst for Community Development",
                "Consultant for Community-Based Organizations"
            )
        )

        ,
        Course(
            title = "Bachelor of Science in Environmental Management",
            overview = "Northwest Samar State University offers a comprehensive Bachelor of Science in Environmental Management program aimed at developing professionals skilled in sustainable resource management and environmental protection. The program integrates scientific principles with policy and management strategies to address environmental challenges.",
            purpose = "The program seeks to prepare graduates who can analyze, plan, and implement effective environmental management practices. NwSSU nurtures environmentally conscious leaders capable of promoting sustainable development and environmental stewardship at local, national, and global levels.",
            learnings = "Students will study environmental science, natural resource management, environmental policy and law, waste management, environmental impact assessment, and sustainable development practices. Fieldwork, laboratory experiments, and community projects equip students with practical skills for environmental problem-solving.",
            careers = listOf(
                "Environmental Manager or Consultant",
                "Sustainability Officer",
                "Natural Resource Manager",
                "Environmental Policy Analyst",
                "Waste Management Specialist",
                "Environmental Impact Assessor",
                "Conservation Officer",
                "Corporate Social Responsibility (CSR) Coordinator",
                "Environmental Educator or Advocate",
                "Researcher in Environmental Science and Management"
            )
        ),
        Course(
            title = "Bachelor of Science in Criminology",
            overview = "Northwest Samar State University’s Bachelor of Science in Criminology program prepares students to become knowledgeable and ethical professionals in the field of criminal justice and public safety. The program covers the study of crime, law enforcement, forensic science, and the legal system, combining theory and practical application.",
            purpose = "The program aims to develop competent criminologists equipped with the skills to prevent crime, uphold justice, and contribute to community safety. Graduates are trained to analyze criminal behavior, assist in investigations, and promote law enforcement and rehabilitation efforts.",
            learnings = "Students will gain knowledge in criminal law, criminological theories, forensic science, police administration, crime scene investigation, correctional administration, and community-based crime prevention. Practical training includes internships with law enforcement agencies, courts, and forensic laboratories.",
            careers = listOf(
                "Law Enforcement Officer or Police Officer",
                "Crime Scene Investigator",
                "Forensic Science Technician",
                "Probation or Parole Officer",
                "Correctional Officer or Administrator",
                "Criminal Investigator or Detective",
                "Security Consultant",
                "Legal and Crime Analyst",
                "Community Crime Prevention Officer",
                "Researcher or Educator in Criminology"
            )
        ),
        Course(
            title = "Bachelor of Science in Environmental Science",
            overview = "Northwest Samar State University’s Bachelor of Science in Environmental Science program prepares students to understand, analyze, and address environmental challenges through scientific and sustainable approaches. The program combines interdisciplinary studies in natural sciences, environmental policy, and resource management.",
            purpose = "This program aims to develop environmentally conscious professionals who can contribute to the conservation, protection, and sustainable management of natural resources. Graduates are equipped to work in government agencies, NGOs, and industries focused on environmental preservation and sustainability.",
            learnings = "Students will gain knowledge in ecology, environmental chemistry, environmental monitoring, waste management, environmental laws and regulations, climate change science, and sustainable development. Fieldwork, laboratory research, and community-based projects provide practical experience for real-world environmental problem-solving.",
            careers = listOf(
                "Environmental Scientist or Specialist",
                "Conservation Officer",
                "Environmental Consultant",
                "Natural Resource Manager",
                "Pollution Control Officer",
                "Environmental Impact Assessor",
                "Sustainability Coordinator",
                "Wildlife or Forestry Officer",
                "Environmental Educator or Researcher",
                "Climate Change Analyst"
            )
        ),
        Course(
            title = "Bachelor of Science in Law Enforcement Administration",
            overview = "Northwest Samar State University’s Bachelor of Science in Law Enforcement Administration program prepares students for careers in law enforcement, public safety, and criminal justice administration. The program combines theoretical foundations with practical training in policing, crime prevention, and justice administration.",
            purpose = "This program aims to develop competent and ethical law enforcement professionals who can effectively manage public safety, enforce laws, and uphold justice. Graduates are equipped with leadership skills, legal knowledge, and investigative techniques to serve in government agencies and community safety organizations.",
            learnings = "Students gain knowledge in criminal law, criminology, police administration, forensic science, community policing, criminal investigation, ethics, and human rights. Practical components include field training, simulations, and internships to prepare students for real-world law enforcement challenges.",
            careers = listOf(
                "Police Officer or Law Enforcement Agent",
                "Crime Investigator or Detective",
                "Correctional Facility Administrator",
                "Security Manager",
                "Forensic Science Technician",
                "Public Safety Officer",
                "Community Crime Prevention Specialist",
                "Law Enforcement Trainer or Educator",
                "Probation or Parole Officer",
                "Legal and Criminal Justice Consultant"
            )
        ),
        Course(
            title = "Bachelor of Science in Industrial Security Management",
            overview = "Northwest Samar State University’s Bachelor of Science in Industrial Security Management program trains students to become proficient security professionals specialized in managing industrial and corporate security operations. The program emphasizes risk assessment, security technology, and emergency response planning to protect assets and personnel.",
            purpose = "This program aims to produce competent security managers who can effectively safeguard industrial facilities, ensure compliance with safety regulations, and implement security policies. Graduates are prepared to handle diverse security challenges in industries, government agencies, and private organizations.",
            learnings = "Students learn about physical security systems, security operations management, risk management, loss prevention, emergency preparedness, investigation techniques, and legal aspects of security. Practical training includes drills, case studies, and internships in real industrial security settings.",
            careers = listOf(
                "Industrial Security Manager",
                "Corporate Security Officer",
                "Risk Management Specialist",
                "Loss Prevention Manager",
                "Emergency Response Coordinator",
                "Security Consultant",
                "Facility Security Supervisor",
                "Investigation Officer",
                "Compliance and Safety Officer",
                "Security Systems Analyst"
            )
        )
    )

    val expandedStates = remember { mutableStateMapOf<Int, Boolean>() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Image(
            painter = painterResource(id = R.drawable.shapes),
            contentDescription = "Background shapes",
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.crimlogo),
                    contentDescription = "College of Criminal Justice and Sciences Logo",
                    modifier = Modifier.size(64.dp)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = "College of Criminal Justice and Sciences",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Available Programs",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            HorizontalDivider(
                modifier = Modifier.padding(bottom = 12.dp),
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                itemsIndexed(courses) { index, course ->
                    val isExpanded = expandedStates[index] == true

                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        tonalElevation = 2.dp,
                        color = MaterialTheme.colorScheme.surface,
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize()
                    ) {
                        Column(
                            modifier = Modifier
                                .clickable { expandedStates[index] = !isExpanded }
                                .padding(16.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = course.title,
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.weight(1f)
                                )

                                Icon(
                                    painter = painterResource(
                                        id = if (isExpanded)
                                            R.drawable.ic_arrow_up
                                        else
                                            R.drawable.ic_arrow_down
                                    ),
                                    contentDescription = if (isExpanded) "Collapse" else "Expand",
                                    tint = MaterialTheme.colorScheme.onSurface
                                )
                            }

                            if (isExpanded) {
                                Spacer(modifier = Modifier.height(12.dp))

                                HorizontalDivider(
                                    thickness = 1.dp,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                                )

                                Spacer(modifier = Modifier.height(12.dp))

                                if (course.subMajors.isNotEmpty()) {
                                    Text(
                                        text = "Majors/Specializations",
                                        style = MaterialTheme.typography.titleSmall,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))

                                    Column {
                                        course.subMajors.forEach { major ->
                                            Text(
                                                text = "• $major",
                                                style = MaterialTheme.typography.bodySmall,
                                                modifier = Modifier.padding(start = 8.dp, bottom = 4.dp)
                                            )
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(12.dp))
                                }

                                Column {
                                    Text(
                                        text = "Overview",
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.titleSmall,
                                        modifier = Modifier.padding(bottom = 4.dp)
                                    )
                                    Text(
                                        text = course.overview,
                                        fontSize = 14.sp,
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier.padding(bottom = 12.dp)
                                    )

                                    HorizontalDivider(
                                        thickness = 1.dp,
                                        modifier = Modifier.padding(vertical = 12.dp),
                                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                                    )

                                    Text(
                                        text = "Purpose",
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.titleSmall,
                                        modifier = Modifier.padding(bottom = 4.dp)
                                    )
                                    Text(
                                        text = course.purpose,
                                        fontSize = 14.sp,
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier.padding(bottom = 12.dp)
                                    )

                                    HorizontalDivider(
                                        thickness = 1.dp,
                                        modifier = Modifier.padding(vertical = 12.dp),
                                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                                    )

                                    Text(
                                        text = "What will you learn?",
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.titleSmall,
                                        modifier = Modifier.padding(bottom = 4.dp)
                                    )
                                    Text(
                                        text = course.learnings,
                                        fontSize = 14.sp,
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier.padding(bottom = 12.dp)
                                    )

                                    HorizontalDivider(
                                        thickness = 1.dp,
                                        modifier = Modifier.padding(vertical = 12.dp),
                                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                                    )

                                    Text(
                                        text = "Possible Career Opportunities",
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.titleSmall,
                                        modifier = Modifier.padding(top = 12.dp, bottom = 4.dp),
                                    )

                                    course.careers.forEach { career ->
                                        Text(
                                            text = "• $career",
                                            fontSize = 14.sp,
                                            style = MaterialTheme.typography.bodySmall,
                                            modifier = Modifier.padding(start = 8.dp, bottom = 4.dp)
                                        )
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AgriScreen(navController: NavHostController) {
    val courses = listOf(
        Course(
            title = "Bachelor of Science in Industrial Technology (Continuing)",
            overview = "Northwest Samar State University’s Bachelor of Science in Industrial Technology (Continuing) program is designed for graduates of ladderized or diploma-level industrial technology programs who wish to pursue a full bachelor's degree. It focuses on advancing students’ technical, managerial, and entrepreneurial competencies in various fields of industrial technology.",
            purpose = "The program aims to produce highly skilled technologists who are capable of contributing to industrial development and innovation. It prepares students for supervisory, technical, and entrepreneurial roles in manufacturing, production, and technology-based industries.",
            learnings = "Students will deepen their knowledge in areas such as mechanical and electrical systems, electronics, automotive technology, drafting, woodworking, and industrial safety. The curriculum includes applied research, production planning, quality control, and project management to ensure workplace readiness.",
            careers = listOf(
                "Industrial Technologist",
                "Production Supervisor or Manager",
                "Maintenance and Operations Technician",
                "Manufacturing Technologist",
                "Quality Assurance Specialist",
                "Industrial Trainer or Instructor",
                "Technical Sales Representative",
                "CNC Machine Operator or Programmer",
                "Plant or Facilities Technician",
                "Entrepreneur in Industrial Services"
            ),
            subMajors = listOf(
                "Major in Automotive Technology",
                "Major in Electrical Technology",
                "Major in Electronics Technology",
                "Major in Mechanical Technology"
            )
        ),
        Course(
            title = "Bachelor of Agricultural Technology",
            overview = "Northwest Samar State University’s Bachelor of Agricultural Technology program equips students with the practical and scientific knowledge needed to enhance agricultural productivity and sustainability. The program blends modern agricultural practices with field-based learning to address the challenges in crop, livestock, and agri-based enterprise development.",
            purpose = "This program aims to develop competent, resourceful, and community-oriented agricultural technologists who can contribute to rural development and food security. NwSSU emphasizes innovation, sustainability, and local responsiveness to prepare graduates for impactful roles in agriculture and agribusiness.",
            learnings = "Students will gain hands-on experience in crop production, animal husbandry, agricultural extension, soil and water management, farm mechanization, and agripreneurship. The curriculum also incorporates fieldwork, laboratory experiments, and industry immersion to build technical expertise and leadership in agricultural initiatives.",
            careers = listOf(
                "Agricultural Technician",
                "Farm Manager or Supervisor",
                "Livestock Production Specialist",
                "Crop Production Specialist",
                "Agricultural Extension Worker",
                "Agri-entrepreneur",
                "Soil and Water Conservation Officer",
                "Agricultural Research Assistant",
                "Agricultural Equipment Operator or Manager",
                "Community Agriculture Advocate"
            )
        ),
        Course(
            title = "Bachelor of Science in Food Technology",
            overview = "Northwest Samar State University’s Bachelor of Science in Food Technology program prepares students to become skilled professionals in food science, processing, safety, and innovation. The program integrates scientific knowledge with technological applications to ensure the quality, safety, and nutritional value of food products.",
            purpose = "This program aims to develop competent food technologists who can contribute to the food industry through research, product development, and quality assurance. NwSSU emphasizes sustainability, food security, and public health in training graduates to meet industry standards and consumer needs.",
            learnings = "Students will study food chemistry, microbiology, food processing and preservation, quality control, food safety regulations, product development, and nutrition. Hands-on laboratory work, industry internships, and research projects prepare students for real-world challenges in the food technology sector.",
            careers = listOf(
                "Food Technologist",
                "Quality Assurance Analyst",
                "Product Development Specialist",
                "Food Safety Officer",
                "Research and Development Scientist",
                "Nutrition Analyst",
                "Food Production Supervisor",
                "Packaging Technologist",
                "Regulatory Affairs Specialist",
                "Entrepreneur in Food Processing"
            )
        ),
        Course(
            title = "Bachelor of Science in Agriculture",
            overview = "Northwest Samar State University’s Bachelor of Science in Agriculture program equips students with the knowledge and practical skills to enhance agricultural productivity, sustainability, and innovation. The program provides a strong foundation in agricultural sciences, technology, and agribusiness to meet the needs of rural development and food security.",
            purpose = "The program aims to produce skilled and ethical agricultural professionals who can contribute to sustainable farming, rural livelihood improvement, and agricultural innovation. NwSSU emphasizes environmental stewardship, scientific research, and community engagement in developing future agricultural leaders.",
            learnings = "Students will study crop science, animal science, soil science, agricultural economics, farm management, agribusiness, agricultural extension, and sustainable farming practices. Practical training, laboratory work, fieldwork, and internships are integral parts of the program to ensure hands-on learning.",
            careers = listOf(
                "Agronomist",
                "Animal Science Specialist",
                "Farm Manager",
                "Agricultural Extension Officer",
                "Soil and Crop Scientist",
                "Agribusiness Entrepreneur",
                "Research and Development Specialist",
                "Agricultural Technician",
                "Sustainable Farming Consultant",
                "Policy Analyst in Agriculture"
            )
        )
    )

    val expandedStates = remember { mutableStateMapOf<Int, Boolean>() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Image(
            painter = painterResource(id = R.drawable.shapes),
            contentDescription = "Background shapes",
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.agrilogo),
                    contentDescription = "College of Agriculture and Technology Logo",
                    modifier = Modifier.size(64.dp)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = "College of Agriculture and Technology",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Available Programs",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            HorizontalDivider(
                modifier = Modifier.padding(bottom = 12.dp),
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                itemsIndexed(courses) { index, course ->
                    val isExpanded = expandedStates[index] == true

                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        tonalElevation = 2.dp,
                        color = MaterialTheme.colorScheme.surface,
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize()
                    ) {
                        Column(
                            modifier = Modifier
                                .clickable { expandedStates[index] = !isExpanded }
                                .padding(16.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = course.title,
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.weight(1f)
                                )

                                Icon(
                                    painter = painterResource(
                                        id = if (isExpanded)
                                            R.drawable.ic_arrow_up
                                        else
                                            R.drawable.ic_arrow_down
                                    ),
                                    contentDescription = if (isExpanded) "Collapse" else "Expand",
                                    tint = MaterialTheme.colorScheme.onSurface
                                )
                            }

                            if (isExpanded) {
                                Spacer(modifier = Modifier.height(12.dp))

                                HorizontalDivider(
                                    thickness = 1.dp,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                                )

                                Spacer(modifier = Modifier.height(12.dp))

                                if (course.subMajors.isNotEmpty()) {
                                    Text(
                                        text = "Majors/Specializations",
                                        style = MaterialTheme.typography.titleSmall,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))

                                    Column {
                                        course.subMajors.forEach { major ->
                                            Text(
                                                text = "• $major",
                                                style = MaterialTheme.typography.bodySmall,
                                                modifier = Modifier.padding(start = 8.dp, bottom = 4.dp)
                                            )
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(12.dp))
                                }

                                Column {
                                    Text(
                                        text = "Overview",
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.titleSmall,
                                        modifier = Modifier.padding(bottom = 4.dp)
                                    )
                                    Text(
                                        text = course.overview,
                                        fontSize = 14.sp,
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier.padding(bottom = 12.dp)
                                    )

                                    HorizontalDivider(
                                        thickness = 1.dp,
                                        modifier = Modifier.padding(vertical = 12.dp),
                                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                                    )

                                    Text(
                                        text = "Purpose",
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.titleSmall,
                                        modifier = Modifier.padding(bottom = 4.dp)
                                    )
                                    Text(
                                        text = course.purpose,
                                        fontSize = 14.sp,
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier.padding(bottom = 12.dp)
                                    )

                                    HorizontalDivider(
                                        thickness = 1.dp,
                                        modifier = Modifier.padding(vertical = 12.dp),
                                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                                    )

                                    Text(
                                        text = "What will you learn?",
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.titleSmall,
                                        modifier = Modifier.padding(bottom = 4.dp)
                                    )
                                    Text(
                                        text = course.learnings,
                                        fontSize = 14.sp,
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier.padding(bottom = 12.dp)
                                    )

                                    HorizontalDivider(
                                        thickness = 1.dp,
                                        modifier = Modifier.padding(vertical = 12.dp),
                                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                                    )

                                    Text(
                                        text = "Possible Career Opportunities",
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.titleSmall,
                                        modifier = Modifier.padding(top = 12.dp, bottom = 4.dp),
                                    )

                                    course.careers.forEach { career ->
                                        Text(
                                            text = "• $career",
                                            fontSize = 14.sp,
                                            style = MaterialTheme.typography.bodySmall,
                                            modifier = Modifier.padding(start = 8.dp, bottom = 4.dp)
                                        )
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun NursingScreen(navController: NavHostController) {
    val courses = listOf(
        Course(
            title = "Bachelor of Science in Nursing",
            overview = "Northwest Samar State University’s Bachelor of Science in Nursing program prepares students to become competent and compassionate healthcare professionals. With a curriculum grounded in science and patient-centered care, the program equips future nurses to respond to the evolving needs of individuals, families, and communities.",
            purpose = "The program aims to develop skilled, ethical, and service-oriented nurses who are capable of providing safe and holistic care across diverse healthcare settings. NwSSU emphasizes critical thinking, evidence-based practice, and lifelong learning to prepare graduates for local and global healthcare environments.",
            learnings = "Students will study anatomy and physiology, microbiology, pharmacology, nursing care management, community health, maternal and child nursing, psychiatric nursing, and nursing research. Clinical practicum and simulations are integral to the program, providing real-world experience in hospitals and community health settings.",
            careers = listOf(
                "Registered Nurse (Hospital or Clinic)",
                "Community Health Nurse",
                "School Nurse",
                "Nurse Educator",
                "Occupational Health Nurse",
                "Clinical Instructor",
                "Public Health Nurse",
                "Nurse Researcher",
                "Home Health Nurse",
                "Healthcare Administrator"
            )
        )
    )

    val expandedStates = remember { mutableStateMapOf<Int, Boolean>() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Image(
            painter = painterResource(id = R.drawable.shapes),
            contentDescription = "Background shapes",
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.nursinglogo),
                    contentDescription = "College of Nursing Logo",
                    modifier = Modifier.size(64.dp)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = "College of Nursing",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Available Programs",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            HorizontalDivider(
                modifier = Modifier.padding(bottom = 12.dp),
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                itemsIndexed(courses) { index, course ->
                    val isExpanded = expandedStates[index] == true

                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        tonalElevation = 2.dp,
                        color = MaterialTheme.colorScheme.surface,
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize()
                    ) {
                        Column(
                            modifier = Modifier
                                .clickable { expandedStates[index] = !isExpanded }
                                .padding(16.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = course.title,
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.weight(1f)
                                )

                                Icon(
                                    painter = painterResource(
                                        id = if (isExpanded)
                                            R.drawable.ic_arrow_up
                                        else
                                            R.drawable.ic_arrow_down
                                    ),
                                    contentDescription = if (isExpanded) "Collapse" else "Expand",
                                    tint = MaterialTheme.colorScheme.onSurface
                                )
                            }

                            if (isExpanded) {
                                Spacer(modifier = Modifier.height(12.dp))

                                HorizontalDivider(
                                    thickness = 1.dp,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                                )

                                Spacer(modifier = Modifier.height(12.dp))

                                if (course.subMajors.isNotEmpty()) {
                                    Text(
                                        text = "Majors/Specializations",
                                        style = MaterialTheme.typography.titleSmall,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))

                                    Column {
                                        course.subMajors.forEach { major ->
                                            Text(
                                                text = "• $major",
                                                style = MaterialTheme.typography.bodySmall,
                                                modifier = Modifier.padding(start = 8.dp, bottom = 4.dp)
                                            )
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(12.dp))
                                }

                                Column {
                                    Text(
                                        text = "Overview",
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.titleSmall,
                                        modifier = Modifier.padding(bottom = 4.dp)
                                    )
                                    Text(
                                        text = course.overview,
                                        fontSize = 14.sp,
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier.padding(bottom = 12.dp)
                                    )

                                    HorizontalDivider(
                                        thickness = 1.dp,
                                        modifier = Modifier.padding(vertical = 12.dp),
                                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                                    )

                                    Text(
                                        text = "Purpose",
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.titleSmall,
                                        modifier = Modifier.padding(bottom = 4.dp)
                                    )
                                    Text(
                                        text = course.purpose,
                                        fontSize = 14.sp,
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier.padding(bottom = 12.dp)
                                    )

                                    HorizontalDivider(
                                        thickness = 1.dp,
                                        modifier = Modifier.padding(vertical = 12.dp),
                                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                                    )

                                    Text(
                                        text = "What will you learn?",
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.titleSmall,
                                        modifier = Modifier.padding(bottom = 4.dp)
                                    )
                                    Text(
                                        text = course.learnings,
                                        fontSize = 14.sp,
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier.padding(bottom = 12.dp)
                                    )

                                    HorizontalDivider(
                                        thickness = 1.dp,
                                        modifier = Modifier.padding(vertical = 12.dp),
                                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                                    )

                                    Text(
                                        text = "Possible Career Opportunities",
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.titleSmall,
                                        modifier = Modifier.padding(top = 12.dp, bottom = 4.dp),
                                    )

                                    course.careers.forEach { career ->
                                        Text(
                                            text = "• $career",
                                            fontSize = 14.sp,
                                            style = MaterialTheme.typography.bodySmall,
                                            modifier = Modifier.padding(start = 8.dp, bottom = 4.dp)
                                        )
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GradScreen(navController: NavHostController) {

    val courses = listOf(
        Course(
            title = "Master of Arts in Education",
            overview = "Northwest Samar State University’s Master of Arts in Education program is designed for educators and professionals seeking advanced knowledge and leadership skills in the field of education. The program emphasizes academic excellence, research competence, and innovative pedagogical practices to enhance the quality of teaching and learning.",
            purpose = "The program aims to develop educational leaders, curriculum experts, and research-driven practitioners who can effectively address contemporary challenges in education. NwSSU fosters a culture of inquiry, reflective practice, and service-oriented leadership to uplift the educational system locally and globally.",
            learnings = "Graduate students engage in advanced coursework in educational leadership, curriculum design, assessment and evaluation, research methodology, policy analysis, and specialized areas such as guidance and counseling or educational management. The program includes thesis writing and action research to ensure scholarly and practical contributions to the field.",
            careers = listOf(
                "School Principal or Administrator",
                "Curriculum and Instruction Specialist",
                "Education Program Supervisor",
                "College or University Faculty",
                "Educational Researcher",
                "Instructional Materials Developer",
                "Policy Analyst in Education",
                "Academic Consultant or Adviser",
                "Guidance Counselor (with appropriate specialization)",
                "Leadership roles in government or non-government education sectors"
            ),
            subMajors = listOf(
                "Major in Administration and Supervision",
                "Major in Curriculum and Instruction",
                "Major in Language Teaching",
                "Major in Cultural Education"
            )
            ),
        Course(
            title = "Master in Public Management",
            overview = "Northwest Samar State University’s Master in Public Management program prepares professionals for leadership and management roles in public service. The program blends theory and practice in governance, policy analysis, and strategic management to address complex challenges in the public and non-profit sectors.",
            purpose = "This program aims to cultivate ethical, effective, and visionary public leaders who can promote good governance, transparency, and community development. NwSSU empowers students with the competencies needed to drive innovation and accountability in local, regional, and national government institutions.",
            learnings = "Graduate students will explore public administration theories, organizational behavior, public fiscal management, human resource development, strategic planning, governance reforms, and research methods. The program emphasizes evidence-based decision-making and includes a capstone project or thesis to solve real-world governance issues.",
            careers = listOf(
                "Public Administrator or Government Executive",
                "Local Government Unit (LGU) Officer",
                "Policy Analyst or Legislative Aide",
                "Project Manager in public and non-profit sectors",
                "Development Planner or Program Coordinator",
                "Public Affairs or Communications Officer",
                "Human Resource Manager in public institutions",
                "Government Auditor or Budget Analyst",
                "Consultant in Governance and Public Sector Reform",
                "Educator or Researcher in Public Administration"
            )
        )
        ,
        Course(
            title = "Master of Arts in Science Teaching",
            overview = "Northwest Samar State University’s Master of Arts in Science Teaching program enhances the pedagogical and scientific competencies of educators aiming to elevate the quality of science instruction in secondary and tertiary levels. It blends advanced scientific knowledge with effective teaching strategies tailored for diverse learners.",
            purpose = "This program aims to produce reflective, research-oriented, and innovative science educators capable of leading curricular reforms, fostering scientific inquiry, and promoting STEM education. NwSSU equips teachers to become leaders in science teaching, curriculum design, and community-based science advocacy.",
            learnings = "Graduate students will engage in advanced coursework in physical and biological sciences, science curriculum development, laboratory teaching, assessment of learning, action research, and science education trends. The program includes thesis work or research projects that contribute to the advancement of science instruction.",
            careers = listOf(
                "Master Teacher in Secondary or Tertiary Education",
                "Science Curriculum Developer",
                "Instructional Materials Writer or Content Developer",
                "Science Department Head or Academic Coordinator",
                "STEM Education Advocate or Trainer",
                "Educational Researcher in Science Pedagogy",
                "College or University Lecturer in Science",
                "Education Program Specialist for Science",
                "Public or Private School Administrator",
                "Science Outreach Program Leader"
            ),
            subMajors = listOf(
                "Major in General Science",
                "Major in Mathematics"
            )
        ),
        Course(
            title = "Master in Library Administration",
            overview = "Northwest Samar State University’s Master in Library Administration program prepares library professionals for leadership roles in managing academic, public, and specialized libraries. It combines principles of library science, information technology, and organizational management to develop efficient and forward-thinking library administrators.",
            purpose = "The program aims to produce competent, ethical, and visionary library leaders capable of managing modern library systems and services. NwSSU fosters innovation, digital transformation, and lifelong learning to support educational and research institutions in the digital age.",
            learnings = "Graduate students will study advanced library management, information organization and retrieval, digital libraries, collection development, research methods, user services, and policy development. Practical training, administrative internships, and a capstone or thesis project ensure professional growth and sector relevance.",
            careers = listOf(
                "Library Director or Chief Librarian",
                "Library Systems and Services Manager",
                "Academic or Public Library Administrator",
                "Knowledge Management Specialist",
                "Digital Library Manager",
                "Library and Information Science Educator",
                "Information Resource Consultant",
                "Cataloging and Metadata Expert",
                "Records and Archives Manager",
                "Research Librarian or Policy Advisor"
            )
        ),
        Course(
            title = "Master of Science in Guidance and Counseling",
            overview = "Northwest Samar State University’s Master of Science in Guidance and Counseling program develops highly skilled professionals equipped to provide effective mental health support, career guidance, and personal development services. The program integrates psychological theories, counseling techniques, and research to prepare graduates for diverse counseling roles.",
            purpose = "The program aims to produce competent, ethical, and empathetic counselors capable of addressing individual, group, and community needs. NwSSU emphasizes holistic development, crisis intervention, and lifelong learning to foster well-being and resilience in various populations.",
            learnings = "Students gain expertise in counseling theories and approaches, psychological assessment, human development, career counseling, group dynamics, ethics, and research methodologies. Practicum, internships, and thesis work provide hands-on experience and professional readiness.",
            careers = listOf(
                "Professional Counselor in schools, hospitals, or community centers",
                "Career Guidance Specialist",
                "Mental Health Counselor",
                "Rehabilitation Counselor",
                "Substance Abuse Counselor",
                "Marriage and Family Therapist",
                "Academic Advisor or Student Affairs Officer",
                "Crisis Intervention Specialist",
                "Researcher in Psychology and Counseling",
                "Consultant in Human Development and Wellness"
            )
        ),
        Course(
            title = "Master in Business Administration",
            overview = "Northwest Samar State University’s Master in Business Administration program offers an advanced curriculum designed to develop strategic thinkers and effective leaders in the business world. The program integrates core business disciplines with real-world applications to prepare graduates for managerial and executive roles.",
            purpose = "This program aims to cultivate innovative, ethical, and results-driven business professionals who can navigate complex organizational challenges and drive sustainable growth. NwSSU focuses on leadership development, strategic decision-making, and global business perspectives.",
            learnings = "Students acquire expertise in finance, marketing, operations, organizational behavior, business analytics, and entrepreneurship. Through case studies, research projects, and internships, students enhance their analytical, communication, and leadership skills essential for high-level management.",
            careers = listOf(
                "Business Manager or Executive",
                "Financial Analyst or Consultant",
                "Marketing Director or Brand Manager",
                "Operations Manager",
                "Entrepreneur or Business Owner",
                "Human Resources Manager",
                "Project Manager",
                "Management Consultant",
                "Corporate Strategist",
                "Business Development Officer"
            )
        ),
        Course(
            title = "Master in Engineering Major in Engineering Management",
            overview = "Northwest Samar State University’s Master in Engineering program with a major in Engineering Management combines advanced engineering principles with management skills to prepare professionals for leadership roles in technical environments. The program bridges the gap between engineering and business management for effective project and organizational oversight.",
            purpose = "This program aims to develop competent engineering managers who can lead multidisciplinary teams, optimize resources, and drive innovation in engineering projects and organizations. NwSSU focuses on strategic planning, quality management, and sustainable engineering practices.",
            learnings = "Students gain knowledge in project management, systems engineering, operations research, quality assurance, financial analysis, and leadership. Through case studies, research, and practical applications, students enhance their ability to manage complex engineering projects and lead teams effectively.",
            careers = listOf(
                "Engineering Manager",
                "Project Manager",
                "Operations Manager",
                "Quality Assurance Manager",
                "Technical Consultant",
                "Process Improvement Specialist",
                "Production Manager",
                "Research and Development Manager",
                "Facilities Manager",
                "Engineering Executive"
            )
        ),
        Course(
            title = "Master of Science in Information Technology",
            overview = "Northwest Samar State University’s Master of Science in Information Technology program offers advanced studies that deepen knowledge in IT theories, methodologies, and emerging technologies. The program prepares graduates to lead innovation and solve complex problems in various IT domains.",
            purpose = "This program aims to develop highly skilled IT professionals who can design, implement, and manage sophisticated information systems and technology projects. NwSSU emphasizes research, strategic IT management, and ethical practices to prepare graduates for leadership roles in the IT industry.",
            learnings = "Students explore advanced topics including software engineering, cybersecurity, data analytics, cloud computing, network architecture, and IT governance. Research projects, case studies, and hands-on applications ensure graduates gain both theoretical understanding and practical skills.",
            careers = listOf(
                "IT Manager or Director",
                "Cybersecurity Specialist",
                "Data Scientist or Analyst",
                "Software Architect",
                "Network Systems Administrator",
                "Cloud Solutions Architect",
                "IT Project Manager",
                "Researcher in Information Technology",
                "Consultant for IT Solutions",
                "Technology Innovator and Entrepreneur"
            )
        ),
        Course(
            title = "Master of Arts in Mathematics Education",
            overview = "Northwest Samar State University’s Master of Arts in Mathematics Education program is designed to deepen the understanding of mathematical concepts and effective teaching strategies. The program prepares educators to enhance mathematics learning and contribute to educational research and curriculum development.",
            purpose = "This program aims to develop highly qualified mathematics educators who are proficient in both advanced mathematics and pedagogical skills. Graduates are equipped to improve mathematics instruction, engage learners, and support academic excellence in various educational settings.",
            learnings = "Students will study advanced topics in algebra, calculus, statistics, and geometry alongside courses in curriculum design, assessment, educational technology, and research methodologies. The program includes practicum and thesis work to develop both theoretical knowledge and practical skills.",
            careers = listOf(
                "Mathematics Teacher or Lecturer in secondary and tertiary levels",
                "Curriculum Developer for Mathematics education",
                "Academic Coordinator or Department Chair",
                "Education Researcher specializing in Mathematics pedagogy",
                "Assessment Specialist in Mathematics learning",
                "Teacher Trainer or Professional Development Facilitator",
                "Math Education Consultant",
                "Educational Technologist in Math-related tools",
                "Graduate Studies Researcher",
                "Mathematics Curriculum Writer or Content Developer"
            )
        ),
        Course(
            title = "Doctor of Education",
            overview = "Northwest Samar State University’s Doctor of Education program is designed to develop advanced educational leaders, researchers, and practitioners capable of shaping education policy and practice. The program focuses on leadership, research, and innovation in education across various specializations.",
            purpose = "This doctoral program aims to prepare educators and administrators for high-level roles in educational institutions, government agencies, and research organizations. Graduates are equipped to influence educational reforms, lead academic institutions, and contribute original research to advance the field of education.",
            learnings = "Students engage in rigorous coursework on educational theories, advanced research methodologies, policy analysis, curriculum leadership, and educational technology. The program culminates in a dissertation that contributes new knowledge to education and demonstrates scholarly excellence.",
            careers = listOf(
                "University Professor or Academic Researcher",
                "Educational Administrator or School Superintendent",
                "Curriculum and Instruction Director",
                "Education Policy Analyst or Consultant",
                "Educational Program Director in government or NGOs",
                "Instructional Coordinator or Specialist",
                "Education Researcher and Evaluator",
                "Higher Education Leader or Dean",
                "Community Education Advocate and Leader",
                "Author and Speaker in Educational Development"
            )
        ),
        Course(
            title = "Doctor of Management",
            overview = "Northwest Samar State University’s Doctor of Management program is designed to develop advanced professionals and scholars equipped with cutting-edge knowledge and skills in management theory, practice, and research. The program prepares leaders to address complex organizational challenges in diverse industries.",
            purpose = "This program aims to cultivate visionary management experts who can lead organizations towards innovation, sustainable growth, and competitive advantage. Graduates are prepared to influence management practices, policy formulation, and strategic decision-making at the highest levels.",
            learnings = "Students will engage in in-depth study of organizational behavior, strategic management, leadership, operations management, financial analysis, and advanced research methodologies. Emphasis is placed on applied research and the development of solutions to real-world management problems.",
            careers = listOf(
                "Chief Executive Officer (CEO) or Executive Director",
                "Management Consultant or Advisor",
                "Business Strategy Analyst",
                "Operations and Project Manager",
                "Academic Professor in Management",
                "Researcher in Business and Management Studies",
                "Organizational Development Specialist",
                "Policy Maker in Government or Industry",
                "Entrepreneur and Business Leader",
                "Corporate Trainer and Leadership Coach"
            )
        ),
        Course(
            title = "Doctor of Philosophy in Education",
            overview = "Northwest Samar State University’s Doctor of Philosophy in Education program offers advanced academic training aimed at producing scholars, researchers, and educational leaders. The program emphasizes rigorous research, critical analysis, and innovation in various educational disciplines.",
            purpose = "This program aims to develop expert educators and researchers capable of advancing knowledge, influencing educational policies, and driving transformative practices in education locally and globally. Graduates are prepared to contribute to the improvement of educational systems through research and leadership.",
            learnings = "Students will deepen their expertise in educational theories, curriculum design, assessment, policy analysis, and advanced research methodologies. The program encourages original research that addresses educational challenges and fosters innovation in teaching and learning.",
            careers = listOf(
                "University Professor or Academic Researcher",
                "Educational Policy Analyst or Consultant",
                "Curriculum Developer and Instructional Designer",
                "Education Program Director or Administrator",
                "Researcher in Educational Institutions or Think Tanks",
                "Education Specialist in Government or NGOs",
                "PhD Supervisor and Mentor for Graduate Students",
                "Author of Scholarly Publications in Education",
                "Conference Presenter and Keynote Speaker",
                "Leader in Educational Reform and Innovation"
            )
        ),
        Course(
            title = "Doctor of Philosophy in Management",
            overview = "Northwest Samar State University’s Doctor of Philosophy in Management program provides advanced academic and research training focused on leadership, organizational development, and strategic management. The program prepares scholars and practitioners to contribute innovative solutions to complex management challenges in various sectors.",
            purpose = "This program aims to develop expert researchers, thought leaders, and management professionals capable of advancing knowledge, influencing policy, and driving organizational excellence. Graduates are equipped to lead transformative initiatives in business, government, and nonprofit organizations locally and internationally.",
            learnings = "Students will engage in in-depth study of organizational behavior, strategic planning, financial management, human resource development, research methodologies, and leadership theory. The program emphasizes original research to address real-world management issues and promote sustainable growth.",
            careers = listOf(
                "University Professor or Academic Researcher in Management",
                "Organizational Development Consultant",
                "Strategic Planning Director",
                "Senior Management Executive",
                "Policy Analyst in Business and Government",
                "Researcher in Management Think Tanks",
                "Corporate Trainer and Leadership Coach",
                "Entrepreneurship and Innovation Specialist",
                "Author and Speaker on Management Topics",
                "Leader in Business and Public Sector Transformation"
            )
        )
    )

    val expandedStates = remember { mutableStateMapOf<Int, Boolean>() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Image(
            painter = painterResource(id = R.drawable.shapes),
            contentDescription = "Background shapes",
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.gradlogo),
                    contentDescription = "Graduate School Logo",
                    modifier = Modifier.size(64.dp)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = "Graduate School",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Available Programs",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            HorizontalDivider(
                modifier = Modifier.padding(bottom = 12.dp),
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                itemsIndexed(courses) { index, course ->
                    val isExpanded = expandedStates[index] == true

                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        tonalElevation = 2.dp,
                        color = MaterialTheme.colorScheme.surface,
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize()
                    ) {
                        Column(
                            modifier = Modifier
                                .clickable { expandedStates[index] = !isExpanded }
                                .padding(16.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = course.title,
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.weight(1f)
                                )

                                Icon(
                                    painter = painterResource(
                                        id = if (isExpanded)
                                            R.drawable.ic_arrow_up
                                        else
                                            R.drawable.ic_arrow_down
                                    ),
                                    contentDescription = if (isExpanded) "Collapse" else "Expand",
                                    tint = MaterialTheme.colorScheme.onSurface
                                )
                            }

                            if (isExpanded) {
                                Spacer(modifier = Modifier.height(12.dp))

                                HorizontalDivider(
                                    thickness = 1.dp,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                                )

                                Spacer(modifier = Modifier.height(12.dp))

                                if (course.subMajors.isNotEmpty()) {
                                    Text(
                                        text = "Majors/Specializations",
                                        style = MaterialTheme.typography.titleSmall,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))

                                    Column {
                                        course.subMajors.forEach { major ->
                                            Text(
                                                text = "• $major",
                                                style = MaterialTheme.typography.bodySmall,
                                                modifier = Modifier.padding(start = 8.dp, bottom = 4.dp)
                                            )
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(12.dp))
                                }

                                Column {
                                    Text(
                                        text = "Overview",
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.titleSmall,
                                        modifier = Modifier.padding(bottom = 4.dp)
                                    )
                                    Text(
                                        text = course.overview,
                                        fontSize = 14.sp,
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier.padding(bottom = 12.dp)
                                    )

                                    HorizontalDivider(
                                        thickness = 1.dp,
                                        modifier = Modifier.padding(vertical = 12.dp),
                                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                                    )

                                    Text(
                                        text = "Purpose",
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.titleSmall,
                                        modifier = Modifier.padding(bottom = 4.dp)
                                    )
                                    Text(
                                        text = course.purpose,
                                        fontSize = 14.sp,
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier.padding(bottom = 12.dp)
                                    )

                                    HorizontalDivider(
                                        thickness = 1.dp,
                                        modifier = Modifier.padding(vertical = 12.dp),
                                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                                    )

                                    Text(
                                        text = "What will you learn?",
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.titleSmall,
                                        modifier = Modifier.padding(bottom = 4.dp)
                                    )
                                    Text(
                                        text = course.learnings,
                                        fontSize = 14.sp,
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier.padding(bottom = 12.dp)
                                    )

                                    HorizontalDivider(
                                        thickness = 1.dp,
                                        modifier = Modifier.padding(vertical = 12.dp),
                                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                                    )

                                    Text(
                                        text = "Possible Career Opportunities",
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.titleSmall,
                                        modifier = Modifier.padding(top = 12.dp, bottom = 4.dp),
                                    )

                                    course.careers.forEach { career ->
                                        Text(
                                            text = "• $career",
                                            fontSize = 14.sp,
                                            style = MaterialTheme.typography.bodySmall,
                                            modifier = Modifier.padding(start = 8.dp, bottom = 4.dp)
                                        )
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }
    }
}