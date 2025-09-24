# ElevatorPitch - Full Stack Application

A comprehensive full-stack application built with **Apache Maven Spring Boot** backend and **Angular** frontend that helps users create personalized elevator pitches through an interactive questionnaire.

![Disclaimer Page](https://github.com/user-attachments/assets/cf78d075-5d63-44be-a7d4-975f360a4224)

## 🚀 Features

### Frontend (Angular)
- **Disclaimer Page**: Welcome page with terms and conditions acceptance
- **Interactive Questionnaire**: Multi-step form with progress tracking
- **Dynamic Question Flow**: 5 carefully crafted questions about professional background, goals, and strengths
- **Result Page**: Personalized elevator pitch outline generation
- **Responsive Design**: Beautiful gradient UI that works on all devices
- **Session Management**: Anonymous sessions with unique IDs

### Backend (Spring Boot)
- **RESTful APIs**: Complete REST endpoints for questions, answers, and outline generation
- **Database Integration**: H2 in-memory database with JPA/Hibernate
- **CORS Configuration**: Proper cross-origin setup for frontend communication
- **Data Persistence**: All user responses are stored and retrievable
- **Automatic Data Initialization**: Sample questions loaded on startup

## 🛠 Technology Stack

### Backend
- **Framework**: Spring Boot 3.1.5
- **Build Tool**: Apache Maven
- **Database**: H2 (in-memory for development)
- **ORM**: JPA/Hibernate
- **Java Version**: 17

### Frontend
- **Framework**: Angular (latest)
- **Build Tool**: Angular CLI
- **HTTP Client**: Angular HttpClient
- **Routing**: Angular Router
- **Styling**: CSS3 with modern features

## 📱 Application Flow

1. **Landing Page**: Users see disclaimer with terms and conditions
2. **Question Flow**: 5 multiple-choice questions about:
   - Professional background
   - Opportunity type sought
   - Primary strengths
   - Unique qualities
   - Main goals
3. **Result Generation**: Backend creates personalized elevator pitch outline
4. **Final Page**: Users can copy their outline and start over

![Question Page](https://github.com/user-attachments/assets/d8d955d9-85b9-4be0-a1fc-37c9d410775a)
![Result Page](https://github.com/user-attachments/assets/879046d9-1a8c-4bce-87bc-9aba772b052e)

## 🏃‍♂️ Quick Start

### Prerequisites
- Java 17+
- Node.js 18+
- Maven 3.6+

### Backend Setup
```bash
cd backend
mvn clean install
mvn spring-boot:run
```
The backend will start on `http://localhost:8080`

### Frontend Setup
```bash
cd frontend
npm install
npm start
```
The frontend will start on `http://localhost:4200`

## 📚 API Endpoints

### Questions
- `GET /api/questions` - Get all questions
- `GET /api/questions/{id}` - Get specific question

### Answers
- `POST /api/answers` - Submit an answer
- `GET /api/responses/{sessionId}` - Get session responses

### Outline Generation
- `GET /api/outline/{sessionId}` - Generate elevator pitch outline

## 🗃 Database Schema

### Questions Table
- `id` (Long) - Primary key
- `text` (String) - Question text
- `order` (Integer) - Display order

### Question Options Table
- `question_id` (Long) - Foreign key
- `option_text` (String) - Option text

### User Responses Table
- `id` (Long) - Primary key
- `session_id` (String) - Session identifier
- `question_id` (Long) - Foreign key
- `selected_option` (String) - User's choice
- `created_at` (DateTime) - Timestamp

## 🎨 UI Features

- **Modern Gradient Design**: Beautiful purple gradient theme
- **Progress Tracking**: Visual progress bar during questionnaire
- **Responsive Layout**: Works on desktop, tablet, and mobile
- **Interactive Elements**: Hover effects and smooth transitions
- **Loading States**: User-friendly loading indicators
- **Error Handling**: Graceful error messages and retry options

## 🔒 Privacy & Security

- **Anonymous Sessions**: No personal information required
- **Temporary Storage**: Session data automatically cleared
- **CORS Protection**: Proper cross-origin configuration
- **Session Isolation**: Each session is completely independent

## 🧪 Testing

The application has been thoroughly tested with:
- ✅ Backend API endpoints functionality
- ✅ Database operations and data persistence
- ✅ Frontend component rendering and navigation
- ✅ Complete user flow from disclaimer to result
- ✅ Session management and data flow
- ✅ Cross-origin requests between frontend and backend

## 📦 Project Structure

```
ElevatorPitch/
├── backend/
│   ├── src/main/java/com/elevatorpitch/
│   │   ├── config/         # Configuration classes
│   │   ├── controller/     # REST controllers
│   │   ├── dto/           # Data transfer objects
│   │   ├── entity/        # JPA entities
│   │   ├── repository/    # Data repositories
│   │   └── service/       # Business logic
│   └── pom.xml           # Maven configuration
├── frontend/
│   ├── src/app/
│   │   ├── components/    # Angular components
│   │   └── services/     # Angular services
│   └── package.json     # NPM configuration
└── README.md
```

## 🚀 Deployment

### Development
Both applications are configured for local development with:
- Backend: `http://localhost:8080`
- Frontend: `http://localhost:4200`
- Auto-reload enabled for both

### Production
- Backend can be packaged as JAR: `mvn clean package`
- Frontend can be built: `npm run build`
- Database can be switched to PostgreSQL or MySQL

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

---

**Built with ❤️ using Spring Boot and Angular**