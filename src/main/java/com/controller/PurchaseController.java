package com.controller;

import com.service.PurchaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class PurchaseController {
    @Autowired
    PurchaseServiceImpl purchaseService;

    /**
     * 定义JSP视图
     * @return ModelAndView
     */
    @GetMapping("/test")
    public ModelAndView testPage() {
        return new ModelAndView("index.html");
    }

    @PostMapping("/purchase")
    public Result purchase(Long userId, Long productId, Integer quantity) {
        boolean success = purchaseService.purchase(userId, productId, quantity);
        String message = success ? "抢购成功" : "抢购失败";
        return new Result(success, message);
    }

    static class Result {
        private boolean success = false;
        private String message = null;

        public Result() {
        }

        public Result(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        /**** setter and getter ****/

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

    }
}
