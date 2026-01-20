# Setup Guide - Flipr Full-Stack Application

## Quick Start

### Step 1: Backend Setup

```bash
cd flipr-react-app/backend
npm install
```

Create `.env` file:
```env
PORT=5000
MONGODB_URI=mongodb://localhost:27017/flipr-app
```

Start backend:
```bash
npm start
```

Backend runs on: **http://localhost:5000**

### Step 2: Frontend Setup

Open a new terminal:

```bash
cd flipr-react-app/frontend
npm install
npm start
```

Frontend runs on: **http://localhost:3000**

## MongoDB Setup Options

### Option 1: Local MongoDB

1. Install MongoDB: https://www.mongodb.com/try/download/community
2. Start MongoDB service
3. Use: `MONGODB_URI=mongodb://localhost:27017/flipr-app`

### Option 2: MongoDB Atlas (Recommended)

1. Create account: https://www.mongodb.com/cloud/atlas
2. Create free cluster
3. Create database user
4. Get connection string
5. Use: `MONGODB_URI=mongodb+srv://user:pass@cluster.mongodb.net/flipr-app`

## Testing the Application

1. Open http://localhost:3000 (Landing Page)
2. Open http://localhost:3000/admin (Admin Panel)
3. Add projects/clients from admin panel
4. View them on landing page

## Project Structure

```
flipr-react-app/
├── backend/
│   ├── models/          # Mongoose models
│   ├── routes/          # API routes
│   ├── controllers/     # Controllers
│   ├── middleware/      # Upload & image processing
│   └── uploads/         # Image storage
└── frontend/
    └── src/
        ├── components/  # React components
        ├── pages/       # Page components
        └── services/    # API calls
```

## Troubleshooting

### Port Already in Use
- Change PORT in backend `.env`
- Update proxy in frontend `package.json`

### MongoDB Connection Error
- Check MongoDB is running (local)
- Verify connection string (Atlas)
- Check network access (Atlas)

### Images Not Loading
- Check backend/uploads directory exists
- Verify image paths in database
- Check CORS settings

## Features

✅ React.js Frontend
✅ Express.js Backend
✅ MongoDB Database
✅ Image Upload & Cropping (450x350)
✅ RESTful API
✅ Admin Panel
✅ Landing Page
