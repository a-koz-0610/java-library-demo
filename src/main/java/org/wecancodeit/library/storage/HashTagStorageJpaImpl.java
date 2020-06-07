package org.wecancodeit.library.storage;

import org.springframework.stereotype.Service;
import org.wecancodeit.library.models.HashTag;
import org.wecancodeit.library.storage.repositories.HashTagRepository;

@Service
public class HashTagStorageJpaImpl implements HashTagStorage {
    private final HashTagRepository hashTagRepository;

    public HashTagStorageJpaImpl(HashTagRepository hashTagRepository) {
        this.hashTagRepository = hashTagRepository;
    }

    @Override
    public void store(HashTag hashTagToStore) {
        hashTagRepository.save(hashTagToStore);
    }
}
