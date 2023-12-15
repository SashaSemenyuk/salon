package org.salon.controllers;

import org.salon.controllers.main.Attributes;
import org.salon.models.Reviews;
import org.salon.models.Services;
import org.salon.models.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@Controller
public class ReviewsCont extends Attributes {
    @GetMapping("/salon/reviews")
    public String reviews(Model model){
        AddAttributesReviews(model);
        return "reviews";
    }
    @PostMapping("/submitReview")
    public String submitReview(@RequestParam("clientName") String clientName,
                               @RequestParam("clientEmail") String clientEmail,
                               @RequestParam("serviceId") Long serviceId,
                               @RequestParam("masterId") Long masterId,
                               @RequestParam("review") String review,
                               @RequestParam("rating") double rating) {


        // Получаем выбранную услугу и мастера
        Services service = repoServices.findById(serviceId).orElse(null);
        Users master = repoUsers.findById(masterId).orElse(null);

        // Проверяем, что все необходимые объекты получены
        if (service != null && master != null) {
            // Создаем объект отзыва
            Reviews newReview = new Reviews();
            newReview.setClientName(clientName);
            newReview.setClientEmail(clientEmail);
            newReview.setService(service);
            newReview.setMaster(master);
            newReview.setReviewDate(new Date());
            newReview.setComment(review);
            newReview.setRating(rating);

            // Сохраняем отзыв в базе данных
            repoReviews.save(newReview);

            // Обновляем средний рейтинг для услуги
            updateAverageRating(service);

            // Перенаправляем на страницу после отправки отзыва
            return "redirect:/salon/services";
        }

        // Если что-то пошло не так, перенаправляем на страницу с ошибкой
        return "redirect:/error";
    }

    private void updateAverageRating(Services service) {
        // Получаем все отзывы для данной услуги
        List<Reviews> reviews = repoReviews.findByService(service);

        // Вычисляем новый средний рейтинг
        double totalRating = 0.0;
        for (Reviews review : reviews) {
            totalRating += review.getRating();
        }

        double averageRating = totalRating / reviews.size();

        // Обновляем поле averageRating в модели услуги
        service.setAverageRating(averageRating);

        // Сохраняем обновленную услугу в базе данных
        repoServices.save(service);
    }
}
