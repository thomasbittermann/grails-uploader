package uploader

import grails.converters.JSON
import grails.validation.Validateable
import org.springframework.web.multipart.MultipartFile

class UploadController {

    def save() {
        def info = collectFileInfo request.getFiles("uploads")
        render info as JSON
    }

    def saveCmd(UploadCommand uploadCommand) {
        def info = collectFileInfo uploadCommand?.uploads
        render info as JSON
    }

    private collectFileInfo(uploads) {
        def info = []
        uploads?.each { it ->
            info << [name: it.originalFilename, size: it.size, contentType: it.contentType]
        }
        return info
    }

}

class UploadCommand implements Validateable {

    MultipartFile[] uploads

    static constraints = {}

}