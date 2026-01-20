# Flipr Full-Stack Application

A complete full-stack web application built with React.js frontend and Node.js/Express backend with MongoDB.

## Tech Stack

- **Frontend**: React.js 18, React Router, Axios
- **Backend**: Node.js, Express.js, Mongoose
- **Database**: MongoDB (MongoDB Atlas)
- **Image Processing**: Multer, Sharp (450x350 cropping)
- **Tools**: VS Code

## Project Structure

```
flipr-react-app/
├── backend/
│   ├── models/          # Mongoose schemas
│   ├── routes/          # API routes
│   ├── controllers/     # Business logic
│   ├── middleware/      # Multer, image processing
│   ├── uploads/         # Uploaded images
│   └── server.js        # Express server
├── frontend/
│   ├── src/
│   │   ├── components/
│   │   │   ├── landing/ # Landing page components
│   │   │   └── admin/   # Admin panel components
│   │   ├── pages/       # Main pages
│   │   ├── services/    # API service layer
│   │   └── App.js       # Main app component
│   └── public/
└── README.md
```

## Features

### Landing Page
- ✅ Our Projects Section (fetches from backend)
- ✅ Happy Clients Section (fetches from backend)
- ✅ Contact Form (submits to backend)
- ✅ Newsletter Subscription

### Admin Panel
- ✅ Project Management (Add/Delete with image upload)
- ✅ Client Management (Add/Delete with image upload)
- ✅ Contact Form Details (View all submissions)
- ✅ Newsletter Subscriptions (View all emails)

### Image Cropping
- ✅ Automatic 450x350 cropping using Sharp
- ✅ Images processed server-side

## Installation & Setup

### Prerequisites
- Node.js (v14+)
- MongoDB (local or MongoDB Atlas account)

### Backend Setup

1. **Navigate to backend directory**:
   ```bash
   cd flipr-react-app/backend
   ```

2. **Install dependencies**:
   ```bash
   npm install
   ```

3. **Configure environment variables**:
   Create `.env` file:
   ```env
   PORT=5000
   MONGODB_URI=mongodb://localhost:27017/flipr-app
   ```
   
   For MongoDB Atlas:
   ```env
   PORT=5000
   MONGODB_URI=mongodb+srv://username:password@cluster.mongodb.net/flipr-app?retryWrites=true&w=majority
   ```

4. **Start backend server**:
   ```bash
   npm start
   # or for development with auto-reload
   npm run dev
   ```

   Backend will run on http://localhost:5000

### Frontend Setup

1. **Navigate to frontend directory**:
   ```bash
   cd flipr-react-app/frontend
   ```

2. **Install dependencies**:
   ```bash
   npm install
   ```

3. **Start development server**:
   ```bash
   npm start
   ```

   Frontend will run on http://localhost:3000

## API Endpoints

### Projects
- `GET /api/projects` - Get all projects
- `POST /api/projects` - Create project (multipart/form-data)
- `DELETE /api/projects/:id` - Delete project

### Clients
- `GET /api/clients` - Get all clients
- `POST /api/clients` - Create client (multipart/form-data)
- `DELETE /api/clients/:id` - Delete client

### Contacts
- `POST /api/contacts` - Submit contact form
- `GET /api/contacts` - Get all contacts

### Newsletter
- `POST /api/newsletters` - Subscribe to newsletter
- `GET /api/newsletters` - Get all subscriptions

## Usage

1. **Start both servers** (backend and frontend)

2. **Access Landing Page**: http://localhost:3000

3. **Access Admin Panel**: http://localhost:3000/admin

4. **Add Projects/Clients**: Use admin panel to add content with images

5. **View Content**: Landing page will automatically display added content

## Image Processing

- Images are automatically cropped to **450x350** pixels
- Server-side processing using Sharp
- Images stored in `backend/uploads/` directory
- Image paths stored in MongoDB

## Deployment

### Backend Deployment
- Deploy to Heroku, AWS, Google Cloud, etc.
- Set `MONGODB_URI` environment variable
- Ensure `uploads/` directory is writable

### Frontend Deployment
- Build: `npm run build`
- Deploy `build/` folder to Netlify, Vercel, etc.
- Update `REACT_APP_API_URL` in `.env` for production

## License

ISC
