package edu.school21.processor;

import com.google.auto.service.AutoService;
import edu.school21.annotations.HtmlForm;
import edu.school21.annotations.HtmlInput;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@SupportedAnnotationTypes({"edu.school21.annotations.HtmlForm", "edu.school21.annotations.HtmlInput"})
@AutoService(Processor.class)
public class HtmlProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (annotations.isEmpty()) {
            return false;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (Element userForm : roundEnv.getElementsAnnotatedWith(HtmlForm.class)) {
            HtmlForm htmlForm = userForm.getAnnotation(HtmlForm.class);
            stringBuilder.append("<form action = \"");
            stringBuilder.append(htmlForm.action());
            stringBuilder.append("\" method = \"");
            stringBuilder.append(htmlForm.method());
            stringBuilder.append("\">\n");
            List<? extends Element> userFormElements = userForm.getEnclosedElements();
            for (Element field : roundEnv.getElementsAnnotatedWith(HtmlInput.class)) {
                if (!userFormElements.contains(field)) {
                    continue;
                }
                HtmlInput htmlInput = field.getAnnotation(HtmlInput.class);
                stringBuilder.append("<input type = \"");
                stringBuilder.append(htmlInput.type());
                stringBuilder.append("\" name = \"");
                stringBuilder.append(htmlInput.name());
                stringBuilder.append("\" placeholder = \"");
                stringBuilder.append(htmlInput.placeholder());
                stringBuilder.append("\">\n");
            }
            stringBuilder.append("<input type = \"submit\" value = \"Send\">\n</form>");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("target/classes/" + htmlForm.fileName()))) {
                writer.write(stringBuilder.toString());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return true;
    }
}
