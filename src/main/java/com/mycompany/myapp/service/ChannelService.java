package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Category;
import com.mycompany.myapp.domain.Chanell;
import com.mycompany.myapp.repository.CategoryRepository;
import com.mycompany.myapp.repository.ChanellRepository;
import com.mycompany.myapp.service.dto.ChanellPostDTO;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ChannelService {

    private final ChanellRepository chanellRepository;
    private final CategoryRepository categoryRepository;

    public ChannelService(ChanellRepository chanellRepository, CategoryRepository categoryRepository) {
        this.chanellRepository = chanellRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Chanell> getChannelsByCategoryId(Long categoryId) {
        Category category = categoryRepository
            .findById(categoryId)
            .orElseThrow(() -> new BadRequestAlertException("Category not found", "Category", "id not found"));
        return chanellRepository.getAllByCategoryIdsAndCityEntity(category, null);
    }

    public Chanell createChannelByCategory(ChanellPostDTO chanellPostDTO) {
        Category category = categoryRepository
            .findById(chanellPostDTO.getIdCat())
            .orElseThrow(
                () -> {
                    throw new BadRequestAlertException("Category in DB", "Category", "id not found");
                }
            );
        HashSet categories = new HashSet<>();
        categories.add(category);

        Chanell chanell = new Chanell();
        chanell.setName(chanellPostDTO.getName());
        chanell.setScore(chanellPostDTO.getScore());
        chanell.setLink(chanellPostDTO.getLink());
        chanell.setIsModerate(chanellPostDTO.getIsModerate());
        chanell.setContacts(chanellPostDTO.getContacts());
        chanell.setComment(chanellPostDTO.getComment());
        chanell.setPriceForPay(chanellPostDTO.getPriceForPay());
        chanell.setIsPay(chanellPostDTO.getIsPay());
        chanell.setStartDate(ZonedDateTime.now());
        chanell.setLastPayDate(chanellPostDTO.getLastPayDate());
        chanell.setEndPublicDate(chanellPostDTO.getEndPublicDate());
        chanell.setCategoryIds(categories);

        return chanellRepository.save(chanell);
    }
}
