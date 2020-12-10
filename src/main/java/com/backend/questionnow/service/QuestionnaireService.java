package com.backend.questionnow.service;

import com.backend.questionnow.dto.QuestionnaireDto;
import com.backend.questionnow.entity.Answer;
import com.backend.questionnow.entity.Question;
import com.backend.questionnow.entity.Questionnaire;
import com.backend.questionnow.entity.User;
import com.backend.questionnow.repository.QuestionnaireRepository;
import com.backend.questionnow.security.CustomException;
import javassist.NotFoundException;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;


@Service
public class QuestionnaireService {

    @Autowired
    QuestionnaireRepository questionnaireRepository;

    @Autowired
    UserService userService;

    public Questionnaire saveQuestionnaire(Questionnaire questionnaire) {
        return questionnaireRepository.save(questionnaire);
    }

    public Questionnaire createQuestionnaire(Questionnaire questionnaire, Long userId)
    {
        User user = userService.findUserById(userId);
        user.getQuestionnaireList().add(questionnaire);
        userService.saveUser(user);
        return questionnaire;
    }

    public List<QuestionnaireDto> getAllUserQuestionnaires(Long userId) throws CustomException {

        User user = userService.findUserById(userId);
        List<Questionnaire> listQuestionnaire = user.getQuestionnaireList();
        return mapQuestionnaireListToQuestionnaireDtoList(listQuestionnaire);
    }

    public Questionnaire changeQuestionnaire(Questionnaire questionnaire)
    {
        return questionnaireRepository.save(questionnaire);
    }

    public Questionnaire getQuestionnaireById(Long questionnaireId) throws CustomException {
        Optional<Questionnaire> questionnaire = questionnaireRepository.findById(questionnaireId);
        return questionnaire.orElseThrow(() -> new CustomException("NotFoundQuestionnaireException","Not Found questionnaire with id: " + questionnaireId, HttpStatus.NOT_FOUND));
    }

    public QuestionnaireDto getStartQuestionnaire(String unicKey) throws CustomException {
        Questionnaire questionnaire = questionnaireRepository.findByUnicKey(unicKey);
        return mapQuestionnaireToQuestionnaireDto(questionnaire);
    }

    public Questionnaire getUserQuestionnaire(Long questionnaireId){
        return getQuestionnaireById(questionnaireId);
    }

    public void deleteQuestionnaire(Long questionnaireId, Long userId){
        Questionnaire questionnaire = getQuestionnaireById(questionnaireId);
        User user = userService.findUserById(userId);
        user.getQuestionnaireList().remove(questionnaire);
        userService.saveUser(user);
    }

    public Questionnaire createQuestionnaireFromPDF(MultipartFile file, Long userId){

        File f = new File("C:\\Users\\user\\Documents\\Projects\\src\\input\\input.pdf");
        try {
            file.transferTo(f);
        } catch (Exception e) {

        }
        String parsedText;
        Questionnaire questionnaire;
        try{
            PDFParser parser = new PDFParser(new RandomAccessFile(f, "r"));
            parser.parse();
            COSDocument cosDoc = parser.getDocument();
            PDFTextStripper pdfStripper = new PDFTextStripper();
            PDDocument pdDoc = new PDDocument(cosDoc);
            parsedText = pdfStripper.getText(pdDoc);
            PrintWriter pw = new PrintWriter("C:\\Users\\user\\Documents\\Projects\\src\\output\\output.txt");
            pw.print(parsedText);
            pw.close();
            File output = new File("C:\\Users\\user\\Documents\\Projects\\src\\output\\output.txt");
            questionnaire = makeQuestionnaire(output);
            createQuestionnaire(questionnaire,userId);

        }

        catch (Exception e){
            throw new CustomException("CreateFromPDFException", "Can not create questionnaire from pdf", HttpStatus.NO_CONTENT);
        }
    return questionnaire;
    }

    private Questionnaire makeQuestionnaire(File output)
    {
        Questionnaire questionnaire = new Questionnaire();
        try{
            Scanner scanner = new Scanner(output);
            while (scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                if (line.contains("[T]"))
                    questionnaire.setName(line.replace("[T]",""));
                if (line.contains("[Q]")) {
                    Question question = new Question(line.replace("[Q]",""),questionnaire.getQuestionList().size()+1L);
                    questionnaire.getQuestionList().add(question);
                }
                if (line.contains("[A]")){
                    int size = questionnaire.getQuestionList().size();
                    Question question = questionnaire.getQuestionList().get(size-1);
                    Answer answer = new Answer(line.replace("[A]","").split("\\[")[0],question.getAnswerList().size()+1L,null,0);
                    question.getAnswerList().add(answer);
                }
            }

        }
        catch (Exception e)
        {
            throw new CustomException("CreateFromPDFException", "Can not create questionnaire from pdf", HttpStatus.NO_CONTENT);
        }
        return questionnaire;
    }


    private QuestionnaireDto mapQuestionnaireToQuestionnaireDto(Questionnaire questionnaire) {
        return new QuestionnaireDto(questionnaire.getQuestionnaireId(), questionnaire.getName(), questionnaire.getFirstQuestion());
    }

    private List<QuestionnaireDto> mapQuestionnaireListToQuestionnaireDtoList(List<Questionnaire> questionnaireList) {
        List<QuestionnaireDto> questionnaireDtoList = new ArrayList<>();
        questionnaireList.stream()
                .forEach(questionnaire -> {
                    try {
                        questionnaireDtoList.add(mapQuestionnaireToQuestionnaireDto(questionnaire));
                    } catch (CustomException e) {
                        e.printStackTrace();
                    }
                });
        return questionnaireDtoList;
    }
}
