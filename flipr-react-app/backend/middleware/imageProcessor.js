const sharp = require('sharp');
const path = require('path');
const fs = require('fs');

/**
 * Process and crop image to exactly 450x350
 * @param {Buffer} imageBuffer - The image buffer from multer
 * @param {String} filename - The filename to save
 * @returns {Promise<String>} - Path to saved image
 */
async function processImage(imageBuffer, filename) {
  const uploadsDir = path.join(__dirname, '../uploads');
  
  // Ensure uploads directory exists
  if (!fs.existsSync(uploadsDir)) {
    fs.mkdirSync(uploadsDir, { recursive: true });
  }

  const outputPath = path.join(uploadsDir, filename);

  // Crop and resize image to exactly 450x350
  await sharp(imageBuffer)
    .resize(450, 350, {
      fit: 'cover',
      position: 'center'
    })
    .jpeg({ quality: 85 })
    .toFile(outputPath);

  return `/uploads/${filename}`;
}

module.exports = { processImage };
