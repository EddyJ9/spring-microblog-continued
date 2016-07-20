package com.theironyard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by EddyJ on 7/19/16.
 */
@Controller
public class MicroblogController {

    @Autowired
    MessageRepository messages;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session){
        String userName = (String) session.getAttribute("userName");
        List<Message> messageList;
        messageList = messages.findAll();

        model.addAttribute("name", userName);
        model.addAttribute("messages", messageList);
        return "home";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String userName){
        session.setAttribute("userName", userName);
        return "redirect:/";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(path = "/add-message", method = RequestMethod.POST)
    public String addMessage(String text){
        Message message = new Message(text);
        messages.save(message);
        return "redirect:/";
    }

    @RequestMapping(path = "/delete-message", method = RequestMethod.POST)
    public String delete(int id){
        Message message = messages.getOne(id);
        messages.delete(message);
        return "redirect:/";
    }

    @RequestMapping(path = "/edit-message", method = RequestMethod.POST)
    public String edit(int id, String text){
        messages.findOne(id);
        Message newMessage = new Message(text);
        messages.save(newMessage);
        return "redirect:/";
    }
}
