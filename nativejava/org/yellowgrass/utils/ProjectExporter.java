package org.yellowgrass.utils;

import com.google.gson.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.webdsl.logging.Logger;
import webdsl.generated.domain.Project;

public class ProjectExporter {
    public static utils.File createExportFile(Project project, JsonObject dbObject, List<AttachmentInfo> attachments) {
        try {
            File outFile = File.createTempFile("yellowgrassexport", ".zip");
            FileOutputStream fos = new FileOutputStream(outFile);
            ZipOutputStream zos = new ZipOutputStream(fos);

            ZipEntry dbFileEntry = new ZipEntry("db-2.0.json");
            zos.putNextEntry(dbFileEntry);
            zos.write(dbObject.toString().getBytes());

            for (AttachmentInfo ai : attachments) {
                ZipEntry attachmentEntry = new ZipEntry(ai.getPath());
                zos.putNextEntry(attachmentEntry);
                ai.getFile().getContentStream().transferTo(zos);
            }

            zos.close();
            fos.close();
            utils.File result = utils.File.createFromFilePath(outFile.getAbsolutePath());
            result.setFileNameForDownload(project.getName() + "-export.zip");
            return result;
        } catch (IOException | SQLException e) {
            Logger.error("Error generating export file: " + e);
            return utils.File.createFromString("Couldn't create export file", "error.txt");
        }
    }
}
