package jee.whmanagement.demo.mapper;

import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class MapperUtil {


    private static ModelMapper modelMapper = new ModelMapper();


    public  static <S, T> List<T>  mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }
}
