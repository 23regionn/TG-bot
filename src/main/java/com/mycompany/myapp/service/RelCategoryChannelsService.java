package com.mycompany.myapp.service;

import static com.mycompany.myapp.service.Constants.*;

import com.mycompany.myapp.domain.Category;
import com.mycompany.myapp.domain.Chanell;
import com.mycompany.myapp.domain.RelCategoryChannels;
import com.mycompany.myapp.domain.ShowChannelsInCategoryLog;
import com.mycompany.myapp.repository.CategoryRepository;
import com.mycompany.myapp.repository.ChanellRepository;
import com.mycompany.myapp.repository.RelCategoryChannelsRepository;
import com.mycompany.myapp.repository.ShowChannelsInCategoryLogRepository;
import com.mycompany.myapp.service.dto.relCategoryChannel.RelCategoryChannelsCreateDTO;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RelCategoryChannelsService {

    private final RelCategoryChannelsRepository relCategoryChannelsRepository;
    private final ChanellRepository chanellRepository;
    private final CategoryRepository categoryRepository;

    private final ShowChannelsInCategoryLogRepository showChannelsInCategoryLogRepository;

    public RelCategoryChannelsService(
        ChanellRepository chanellRepository,
        CategoryRepository categoryRepository,
        RelCategoryChannelsRepository relCategoryChannelsRepository,
        ShowChannelsInCategoryLogRepository showChannelsInCategoryLogRepository
    ) {
        this.chanellRepository = chanellRepository;
        this.categoryRepository = categoryRepository;
        this.relCategoryChannelsRepository = relCategoryChannelsRepository;
        this.showChannelsInCategoryLogRepository = showChannelsInCategoryLogRepository;
    }

    public List<RelCategoryChannels> getAllByCategory(Long categoryId) {
        Category category = categoryRepository
            .findById(categoryId)
            .orElseThrow(
                () -> {
                    throw new BadRequestAlertException(CATEGORY_NOT_FOUND, CATEGORY_NAME, ID_NOT_FOUND);
                }
            );

        return relCategoryChannelsRepository.getAllByCategory(category);
    }

    public RelCategoryChannels createNewRel(RelCategoryChannelsCreateDTO createDTO) {
        Category category = categoryRepository
            .findById(createDTO.getIdCat())
            .orElseThrow(
                () -> {
                    throw new BadRequestAlertException(CATEGORY_NOT_FOUND, CATEGORY_NAME, ID_NOT_FOUND);
                }
            );

        Chanell chanell = chanellRepository
            .findById(createDTO.getIdChannel())
            .orElseThrow(
                () -> {
                    throw new BadRequestAlertException(CHANNEL_NOT_FOUND, CHANNEL_NAME, ID_NOT_FOUND);
                }
            );

        if (!relCategoryChannelsRepository.existsByCategoryAndChanell(category, chanell)) {
            RelCategoryChannels relCategoryChannels = new RelCategoryChannels();
            relCategoryChannels.setCategory(category);
            relCategoryChannels.setChanell(chanell);
            relCategoryChannels.setScoreChannel(createDTO.getScoreChannel());
            relCategoryChannels.setIsShowChannel(createDTO.getIsShowChannel());
            relCategoryChannels.setComment(createDTO.getComment());

            ShowChannelsInCategoryLog audit = new ShowChannelsInCategoryLog();
            audit.setIdCategory(category.getId());
            audit.setNameCategory(category.getName());
            audit.setIdChannel(chanell.getId());
            audit.setNameChannel(chanell.getName());
            audit.setComment(createDTO.getComment());
            audit.setScoreChannel(createDTO.getScoreChannel());
            audit.setIsShowChannel(createDTO.getIsShowChannel());
            audit.setDateLog(LocalDate.now());
            showChannelsInCategoryLogRepository.save(audit);

            return relCategoryChannelsRepository.save(relCategoryChannels);
        } else {
            throw new BadRequestAlertException("Канал уже существет", "Нельзя создать дубль ", "Есть в БД");
        }
    }

    public ShowChannelsInCategoryLog setAuditAfterUpdateRecord(ShowChannelsInCategoryLog audit, RelCategoryChannels rel) {
        Chanell chanell = rel.getChanell();
        Category category = rel.getCategory();

        if (chanell != null) {
            audit.setIdChannel(chanell.getId());
            audit.setNameChannel(chanell.getName());
        }
        if (category != null) {
            audit.setIdCategory(category.getId());
            audit.setNameCategory(category.getName());
        }

        audit.setComment(rel.getComment());
        audit.setScoreChannel(rel.getScoreChannel());
        audit.setIsShowChannel(rel.getIsShowChannel());

        audit.setDateLog(LocalDate.now());
        return showChannelsInCategoryLogRepository.save(audit);
    }
}
