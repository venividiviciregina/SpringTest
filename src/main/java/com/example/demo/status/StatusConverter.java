package com.example.demo.status;

        import com.example.demo.status.Status;

        import javax.persistence.AttributeConverter;
        import javax.persistence.Converter;
        import java.util.stream.Stream;

@Converter
public class StatusConverter implements AttributeConverter<Status, String> {
    @Override
    public String convertToDatabaseColumn( Status status ) {
        return status.dbName();
    }

    @Override
    public Status convertToEntityAttribute( String statusName ) {
        return Stream.of( Status.values() ).filter( status -> status.dbName().equalsIgnoreCase( statusName ) ).findAny().orElseThrow( () -> new IllegalArgumentException( "Status malformed" ) );
    }
}
