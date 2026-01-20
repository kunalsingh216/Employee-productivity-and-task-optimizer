# Technology Stack - Employee Productivity Tool

## Overview
This is a full-stack web application for managing employee productivity, task allocation, and team performance analytics.

---

## Frontend Technologies

### Core Framework & Libraries
- **React 18** - JavaScript library for building user interfaces
  - Used via CDN: `https://unpkg.com/react@18/umd/react.development.js`
  - React Hooks: `useState`, `useEffect`, `useRef`, `useCallback`, `useMemo`
  - React DOM for rendering: `https://unpkg.com/react-dom@18/umd/react-dom.development.js`

### JavaScript Transpilation
- **Babel Standalone** - JavaScript compiler for JSX transformation
  - CDN: `https://unpkg.com/@babel/standalone/babel.min.js`
  - Enables JSX syntax in browser without build step

### Data Visualization
- **Chart.js** - Charting library for data visualization
  - CDN: `https://cdn.jsdelivr.net/npm/chart.js`
  - Used for:
    - Line charts (Productivity Trends)
    - Bar charts (Difficulty Distribution)
    - Doughnut charts (Priority Distribution)

### Frontend Architecture
- **Single Page Application (SPA)** - All content in one HTML file
- **Component-Based Architecture** - Modular React components
- **Client-Side Routing** - Tab-based navigation system

---

## Backend Technologies

### Runtime Environment
- **Node.js** - JavaScript runtime environment
  - Version: Compatible with Node.js 14+

### Web Framework
- **Express.js 5.1.0** - Web application framework for Node.js
  - RESTful API endpoints
  - Middleware support (CORS, JSON parsing)
  - Static file serving

### Database
- **SQLite3 5.1.7** - Lightweight, file-based relational database
  - Database file: `productivity_tool.db`
  - Tables:
    - `employees` - Employee information and metrics
    - `tasks` - Task details and assignments
    - `task_history` - Historical task completion data

### Backend Dependencies
- **CORS 2.8.5** - Cross-Origin Resource Sharing middleware
  - Enables API access from different origins
- **Path** - Node.js built-in module for file path operations

---

## API Architecture

### RESTful API Endpoints

#### Employee Management
- `GET /api/employees` - Retrieve all employees
- `POST /api/employees` - Create new employee
- `DELETE /api/employees/:employeeId` - Remove employee

#### Task Management
- `GET /api/tasks` - Retrieve all tasks (with optional status filter)
- `POST /api/tasks` - Create new task
- `POST /api/tasks/:taskId/assign` - Auto-assign task to employee
- `PUT /api/tasks/:taskId/status` - Update task status

#### Analytics
- `GET /api/analytics/dashboard` - Get dashboard statistics
- `GET /api/analytics/trends` - Get productivity trends data
- `GET /api/recommendations/:employeeId` - Get employee recommendations

---

## Development Tools

### Development Dependencies
- **localtunnel 2.0.2** - Expose local server to the internet (for testing)

### Package Management
- **npm** - Node Package Manager
  - Scripts:
    - `npm start` - Start the server (`node server.js`)

---

## Styling & UI

### CSS Features
- **CSS Variables** - Custom properties for theming
- **CSS Grid** - Layout system for responsive design
- **Flexbox** - Flexible box layout
- **CSS Gradients** - Modern gradient backgrounds
- **Responsive Design** - Mobile-friendly layouts
- **CSS Animations** - Smooth transitions and hover effects

### Design Patterns
- **Card-Based UI** - Content organized in cards
- **Tab Navigation** - Dashboard, Tasks, Employees, Analytics
- **Modal Dialogs** - For forms and detailed views
- **Toast Notifications** - User feedback messages

---

## Key Features & Patterns

### State Management
- React Hooks for local component state
- No external state management library (Redux, Zustand, etc.)

### Data Fetching
- Custom `fetchJSON` function with:
  - Automatic API prefix handling
  - Multiple endpoint fallback support
  - Error handling and retry logic

### Real-Time Updates
- Auto-refresh functionality (5-second intervals)
- Page visibility API integration
- Chart.js real-time data updates

### Algorithm Implementation
- **Task Assignment Algorithm** - Intelligent task-to-employee matching based on:
  - Employee workload
  - Experience level
  - Efficiency score
  - Task difficulty
  - Priority considerations

### Data Processing
- SQL aggregations for analytics
- Time accuracy calculations
- Efficiency score updates
- Workload distribution analysis

---

## Project Structure

```
employee-productivity-tool/
├── index.html          # Frontend (React app + styles)
├── server.js          # Backend (Express API + SQLite)
├── package.json       # Dependencies and scripts
├── productivity_tool.db # SQLite database
└── node_modules/      # Installed packages
```

---

## Browser Compatibility
- Modern browsers supporting:
  - ES6+ JavaScript features
  - React 18
  - Fetch API
  - CSS Grid and Flexbox

---

## Deployment Considerations

### Current Setup
- Development server on port 5501 (configurable via PORT environment variable)
- Single-file frontend (no build step required)
- File-based database (SQLite)

### Production Recommendations
- Build process for React (Webpack/Vite)
- Environment variable configuration
- Database migration strategy
- HTTPS support
- Error logging and monitoring
- Database backup strategy

---

## Security Features

### Implemented
- CORS configuration
- Input validation
- SQL parameterized queries (prevents SQL injection)
- Content Security Policy (CSP) headers

### Recommendations for Production
- Authentication & Authorization
- Rate limiting
- Input sanitization
- HTTPS enforcement
- Database encryption
- API key management

---

## Performance Optimizations

- React memoization (`useMemo`, `useCallback`)
- Chart instance reuse (prevents memory leaks)
- Lazy loading for large datasets
- Efficient SQL queries with indexes
- CSS-based animations (GPU accelerated)

---

## Version Information

- **Project Version**: 1.0.0
- **Express**: ^5.1.0
- **SQLite3**: ^5.1.7
- **React**: 18
- **Chart.js**: Latest (via CDN)

---

## License
ISC

