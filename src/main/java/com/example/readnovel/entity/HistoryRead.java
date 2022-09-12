package com.example.readnovel.entity;

import com.example.readnovel.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "historyRead")
public class HistoryRead extends BaseEntity {
    @Id
    @GeneratedValue(generator = "custom-name")
    @GenericGenerator(name = "custom-name", strategy = "com.example.readnovel.util.CustomId", parameters = @Parameter(name = "prefix", value = "HR"))
    private String id;
    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE})
    @JoinColumn(name = "accountId")
    @JsonBackReference
    private Account account;
    @OneToMany(mappedBy = "historyRead", cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REMOVE})
    @OrderBy("updatedAt")
    @JsonManagedReference
    @BatchSize(size = 30)
    private List<HistoryItem> historyItems;

    public void addToHistory(HistoryItem historyItem) {
        historyItems.add(historyItem);
    }

    public HistoryItem getHistoryItem(Novel novel) {
        HistoryItem item = HistoryItem.builder().novel(novel).historyRead(this).build();
        if (historyItems.contains(item)) {
            Map<String, HistoryItem> historyItemMap = historyItems.stream().collect(Collectors.toMap(historyItem -> historyItem.getNovel().getId(), historyItem -> historyItem));
            return historyItemMap.get(novel.getId());
        }
        return item;
    }

}
