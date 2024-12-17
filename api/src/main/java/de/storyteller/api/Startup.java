package de.storyteller.api;

import de.storyteller.api.model.DbInitialized;
import de.storyteller.api.repository.InitialDataRepository;
import de.storyteller.api.service.ExampleDataGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class Startup {

  private final InitialDataRepository initialDataRepository;
  private final ExampleDataGenerator exampleDataGenerator;

  /**
   * Setup needed data after the application has started, if the database is empty
   * @param event The event that the application has started
   */
  @EventListener
  public void handleApplicationReadyEvent(ApplicationReadyEvent event) {
        if (initialDataRepository.existsById("initialized")){
          log.info("Database already initialized");
        }
        else {
          log.info("Database not initialized yet. Initializing now");
          exampleDataGenerator.generateGenres();
          initialDataRepository.save(new DbInitialized("initialized", true));
          log.info("Database initialized");
        }
  }


}
