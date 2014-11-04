package be.cytomine.image

import http.HttpClient

import javax.imageio.ImageIO
import java.awt.image.BufferedImage

/**
 * Implement generics methods for handling imaging data in controllers
 */
class ImageUtilsController {

    /**
     * Read a picture from url
     * @param url Picture url
     * @return Picture as an object
     */
    protected BufferedImage getImageFromURL(String url) {
        def out = new ByteArrayOutputStream()
        try {
            out << new URL(url).openStream()
        } catch (Exception e) {
            e.printStackTrace()
        }
        InputStream inputStream = new ByteArrayInputStream(out.toByteArray())
        BufferedImage bufferedImage = ImageIO.read(inputStream)
        inputStream.close()
        out.close()
        return bufferedImage
    }

    protected responseFile(File file) {
        BufferedInputStream bufferedInputStream = file.newInputStream()
        response.setHeader "Content-disposition", "attachment; filename=\"${file.getName()}\""
        response.outputStream << bufferedInputStream
        response.outputStream.flush()
        bufferedInputStream.close()
    }


    /**
     * Response an image as a HTTP response
     * @param bufferedImage Image
     */
    protected def responseBufferedImage(BufferedImage bufferedImage) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        withFormat {

            png {
                if (request.method == 'HEAD') {
                    render(text: "", contentType: "image/png")
                }
                else {
                    ImageIO.write(bufferedImage, "png", baos);
                    byte[] bytesOut = baos.toByteArray();
                    response.contentLength = baos.size();
                    response.setHeader("Connection", "Keep-Alive")
                    response.setHeader("Accept-Ranges", "bytes")
                    response.setHeader("Content-Type", "image/png")
                    response.getOutputStream() << bytesOut
                    response.getOutputStream().flush()
                }
            }
            jpg {
                if (request.method == 'HEAD') {
                    render(text: "", contentType: "image/jpeg");
                }
                else {
                    ImageIO.write(bufferedImage, "jpg", baos);
                    byte[] bytesOut = baos.toByteArray();
                    response.contentLength = baos.size();
                    response.setHeader("Connection", "Keep-Alive")
                    response.setHeader("Accept-Ranges", "bytes")
                    response.setHeader("Content-Type", "image/jpeg")
                    response.getOutputStream() << bytesOut
                    response.getOutputStream().flush()
                }
            }
        }
        baos.close()
    }


    /**
     * Response an image as a HTTP response
     * @param url Image url
     */
    protected def responseImageFromUrl(String url) {
        log.info "url2=$url"
        URL source = new URL(url)
        URLConnection connection = source.openConnection()
        response.contentType = 'image/jpeg'
        // Set the content length
        response.setHeader("Content-Length", connection.contentLength.toString())
        // Get the input stream from the connection
        response.outputStream << connection.getInputStream()
    }




}
