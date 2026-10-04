package com.alroru.lifestream.config;

import com.alroru.lifestream.model.Animal;
import com.alroru.lifestream.repository.AnimalRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    final AnimalRepository repository;

    public DataInitializer(AnimalRepository repository) {
        this.repository = repository;
    }
    @Override
    public void run(String... args) throws Exception {
        repository.saveAll(List.of(
                // Iniciales
                new Animal("Zorro rojo", "Vulpes vulpes", "Bosques y praderas", "Omnívoro", "Preocupación menor", "Cánido pequeño y adaptable de pelaje rojizo y cola frondosa"),
                new Animal("Lobo ibérico", "Canis lupus signatus", "Bosques y montañas", "Carnívoro", "Casi amenazado", "Subespecie de lobo de la península ibérica que vive en manadas"),
                new Animal("Lince ibérico", "Lynx pardinus", "Matorral mediterráneo", "Carnívoro", "En peligro", "Felino moteado de orejas con pinceles, especialista en conejo"),
                // Invertebrados
                new Animal("Lombriz de tierra común", "Lumbricus terrestris", "Suelos húmedos", "Detritívoro", "No evaluado", "Gusano anélido que airea el suelo y recicla materia orgánica", "/images/animals/lombriz-de-tierra-comun.jpg"),
                new Animal("Babosa común", "Arion ater", "Jardines y bosques húmedos", "Herbívoro", "No evaluado", "Molusco sin concha de cuerpo oscuro que se alimenta de plantas y hongos", "/images/animals/babosa-comun.jpg"),
                new Animal("Caracol común", "Cornu aspersum", "Jardines y huertos", "Herbívoro", "Preocupación menor", "Molusco de concha espiralada muy común en zonas cultivadas", "/images/animals/caracol-comun.jpg"),
                new Animal("Calamar común", "Loligo vulgaris", "Aguas costeras", "Carnívoro", "Datos insuficientes", "Cefalópodo de nado rápido que caza peces y crustáceos", "/images/animals/calamar-comun.jpg"),
                new Animal("Pulpo común", "Octopus vulgaris", "Fondos rocosos costeros", "Carnívoro", "Preocupación menor", "Cefalópodo de ocho brazos famoso por su inteligencia y camuflaje", "/images/animals/pulpo-comun.jpg"),
                new Animal("Cangrejo verde", "Carcinus maenas", "Costas rocosas y estuarios", "Omnívoro", "No evaluado", "Crustáceo costero muy adaptable y de caparazón verdoso", "/images/animals/cangrejo-verde.jpg"),
                new Animal("Ciempiés común", "Lithobius forficatus", "Bajo piedras y hojarasca", "Carnívoro", "No evaluado", "Miriápodo veloz de cuerpo aplanado que caza pequeños insectos", "/images/animals/ciempies-comun.jpg"),
                new Animal("Milpiés negro", "Tachypodoiulus niger", "Hojarasca de bosques", "Detritívoro", "No evaluado", "Miriápodo cilíndrico de dos pares de patas por segmento", "/images/animals/milpies-negro.jpg"),
                new Animal("Cucaracha americana", "Periplaneta americana", "Ambientes urbanos húmedos", "Omnívoro", "No evaluado", "Insecto corredor de gran tamaño adaptado a la vida urbana", "/images/animals/cucaracha-americana.jpg"),
                new Animal("Mantis religiosa", "Mantis religiosa", "Matorrales y praderas", "Carnívoro", "No evaluado", "Insecto depredador de patas delanteras raptoras y cabeza móvil", "/images/animals/mantis-religiosa.jpg"),
                new Animal("Insecto hoja", "Phyllium philippinicum", "Selvas tropicales", "Herbívoro", "No evaluado", "Insecto con cuerpo en forma de hoja, maestro del camuflaje", "/images/animals/insecto-hoja.jpg"),
                new Animal("Insecto palo", "Carausius morosus", "Matorrales", "Herbívoro", "No evaluado", "Insecto alargado que imita ramitas para pasar desapercibido", "/images/animals/insecto-palo.jpg"),
                new Animal("Mosca doméstica", "Musca domestica", "Entornos urbanos y rurales", "Omnívoro", "No evaluado", "Díptero ubicuo asociado a asentamientos humanos", "/images/animals/mosca-domestica.jpg"),
                new Animal("Mariposa monarca", "Danaus plexippus", "Praderas y jardines", "Herbívoro", "En peligro", "Mariposa migradora naranja y negra ligada a las asclepias", "/images/animals/mariposa-monarca.jpg"),
                new Animal("Mariposa atlas", "Attacus atlas", "Selvas tropicales asiáticas", "Herbívoro", "No evaluado", "Polilla gigante de alas con dibujos que recuerdan cabezas de serpiente", "/images/animals/mariposa-atlas.jpg"),
                new Animal("Abeja europea", "Apis mellifera", "Colmenas, praderas y cultivos", "Herbívoro", "Datos insuficientes", "Himenóptero social clave en la polinización de cultivos", "/images/animals/abeja-europea.jpg"),
                new Animal("Avispa común", "Vespula vulgaris", "Jardines y bosques", "Omnívoro", "No evaluado", "Himenóptero social de abdomen rayado amarillo y negro", "/images/animals/avispa-comun.jpg"),
                new Animal("Hormiga roja", "Myrmica rubra", "Praderas y jardines", "Omnívoro", "No evaluado", "Hormiga rojiza que forma colonias numerosas en el suelo", "/images/animals/hormiga-roja.jpg"),
                new Animal("Mariquita de siete puntos", "Coccinella septempunctata", "Jardines y cultivos", "Carnívoro", "No evaluado", "Escarabajo rojo con siete puntos, devorador de pulgones", "/images/animals/mariquita-siete-puntos.jpg"),
                new Animal("Ciervo volante", "Lucanus cervus", "Bosques de robles", "Herbívoro", "Casi amenazado", "Escarabajo de grandes mandíbulas en forma de asta de ciervo", "/images/animals/ciervo-volante.jpg"),
                new Animal("Escarabajo titán", "Titanus giganteus", "Selva amazónica", "Herbívoro", "No evaluado", "Uno de los insectos más grandes del mundo, de hábitos nocturnos", "/images/animals/escarabajo-titan.jpg"),
                new Animal("Araña de jardín", "Araneus diadematus", "Jardines y setos", "Carnívoro", "No evaluado", "Araña tejedora de telarañas orbiculares con cruz en el abdomen", "/images/animals/arana-de-jardin.jpg"),
                new Animal("Escorpión amarillo", "Buthus occitanus", "Zonas áridas mediterráneas", "Carnívoro", "No evaluado", "Escorpión de cola gruesa y pinzas finas, activo de noche", "/images/animals/escorpion-amarillo.jpg"),
                // Peces
                new Animal("Raya común", "Raja clavata", "Fondos arenosos costeros", "Carnívoro", "Casi amenazado", "Pez de cuerpo aplanado con aguijones en el lomo"),
                new Animal("Tiburón blanco", "Carcharodon carcharias", "Océanos templados y tropicales", "Carnívoro", "Vulnerable", "Gran depredador marino de dientes triangulares aserrados"),
                new Animal("Rape común", "Lophius piscatorius", "Fondos marinos", "Carnívoro", "Preocupación menor", "Pez del fondo marino con señuelo luminoso para atraer presas"),
                new Animal("Pez payaso", "Amphiprion ocellaris", "Arrecifes de coral", "Omnívoro", "Preocupación menor", "Pez naranja de arrecife que vive entre anémonas"),
                new Animal("Trucha común", "Salmo trutta", "Ríos y lagos fríos", "Carnívoro", "Preocupación menor", "Pez de agua dulce moteado muy apreciado en pesca"),
                new Animal("Barracuda", "Sphyraena barracuda", "Aguas tropicales costeras", "Carnívoro", "Preocupación menor", "Pez cazador alargado de mandíbulas potentes y veloz"),
                // Anfibios y reptiles
                new Animal("Tritón crestado", "Triturus cristatus", "Charcas y bosques húmedos", "Carnívoro", "Preocupación menor", "Anfibio de cresta dorsal dentada en época de cría"),
                new Animal("Salamandra común", "Salamandra salamandra", "Bosques húmedos", "Carnívoro", "Preocupación menor", "Anfibio negro con manchas amarillas y piel tóxica"),
                new Animal("Rana común", "Pelophylax perezi", "Charcas y ríos", "Carnívoro", "Preocupación menor", "Anfibio verde de gran salto ligado al agua"),
                new Animal("Sapo común", "Bufo bufo", "Jardines y bosques", "Carnívoro", "Preocupación menor", "Anfibio robusto de piel verrugosa y hábitos nocturnos"),
                new Animal("Tortuga mediterránea", "Testudo hermanni", "Matorral mediterráneo", "Herbívoro", "Casi amenazado", "Tortuga terrestre de caparazón abombado y espolón en la cola"),
                new Animal("Galápago europeo", "Emys orbicularis", "Humedales y ríos lentos", "Omnívoro", "Casi amenazado", "Tortuga acuática de caparazón oscuro con motas amarillas"),
                new Animal("Pitón reticulada", "Malayopython reticulatus", "Selvas del sudeste asiático", "Carnívoro", "Preocupación menor", "Una de las serpientes más largas del mundo, constrictora"),
                new Animal("Boa constrictora", "Boa constrictor", "Selvas americanas", "Carnívoro", "Preocupación menor", "Serpiente que asfixia a sus presas enrollándose en torno a ellas"),
                new Animal("Víbora hocicuda", "Vipera latastei", "Matorrales ibéricos", "Carnívoro", "Vulnerable", "Serpiente venenosa ibérica de hocico respingón"),
                new Animal("Cocodrilo del Nilo", "Crocodylus niloticus", "Ríos y lagos africanos", "Carnívoro", "Preocupación menor", "Gran reptil semiacuático de mandíbulas muy potentes"),
                new Animal("Caimán de anteojos", "Caiman crocodilus", "Humedales americanos", "Carnívoro", "Preocupación menor", "Cocodriliano de tamaño medio con cresta ósea entre los ojos"),
                // Aves
                new Animal("Avestruz", "Struthio camelus", "Sabanas africanas", "Omnívoro", "Preocupación menor", "Ave que no vuela, la más grande y rápida corriendo"),
                new Animal("Ánade azulón", "Anas platyrhynchos", "Lagos y ríos", "Omnívoro", "Preocupación menor", "Pato acuático de cabeza verde iridiscente en los machos"),
                new Animal("Flamenco común", "Phoenicopterus roseus", "Humedales salinos", "Omnívoro", "Preocupación menor", "Ave de ribera rosada de patas y cuello larguísimos"),
                new Animal("Águila real", "Aquila chrysaetos", "Montañas", "Carnívoro", "Preocupación menor", "Ave de presa de gran envergadura y vuelo majestuoso"),
                new Animal("Loro gris", "Psittacus erithacus", "Selvas africanas", "Herbívoro", "En peligro", "Loro famoso por su capacidad para imitar sonidos"),
                new Animal("Periquito común", "Melopsittacus undulatus", "Estepas australianas", "Herbívoro", "Preocupación menor", "Pequeño loro verde de cola larga y vuelo ondulante"),
                new Animal("Búho real", "Bubo bubo", "Roquedos y bosques", "Carnívoro", "Preocupación menor", "Ave nocturna de grandes penachos y mirada penetrante"),
                new Animal("Petirrojo", "Erithacus rubecula", "Jardines y bosques", "Omnívoro", "Preocupación menor", "Pájaro de jardín de pecho rojizo y canto melodioso"),
                new Animal("Ruiseñor", "Luscinia megarhynchos", "Matorrales", "Omnívoro", "Preocupación menor", "Pájaro cantor de plumaje discreto y canto potente"),
                // Mamíferos
                new Animal("Canguro rojo", "Osphranter rufus", "Praderas australianas", "Herbívoro", "Preocupación menor", "Marsupial de gran tamaño que se desplaza a saltos"),
                new Animal("Koala", "Phascolarctos cinereus", "Bosques de eucaliptos", "Herbívoro", "Vulnerable", "Marsupial arborícola que se alimenta casi solo de eucalipto"),
                new Animal("Bandicut marrón", "Isoodon macrourus", "Matorrales australianos", "Omnívoro", "Preocupación menor", "Pequeño marsupial de hocico largo que hoza en el suelo"),
                new Animal("Demonio de Tasmania", "Sarcophilus harrisii", "Bosques de Tasmania", "Carnívoro", "En peligro", "Marsupial carroñero de mandíbula potente y mal genio"),
                new Animal("Perezoso de tres dedos", "Bradypus variegatus", "Copas de selvas americanas", "Herbívoro", "Preocupación menor", "Mamífero arborícola de movimientos lentos y vida colgada"),
                new Animal("Oso hormiguero gigante", "Myrmecophaga tridactyla", "Sabanas y selvas", "Carnívoro", "Vulnerable", "Desdentado de larga lengua que devora hormigas y termitas"),
                new Animal("Armadillo de nueve bandas", "Dasypus novemcinctus", "Praderas y bosques", "Omnívoro", "Preocupación menor", "Mamífero acorazado capaz de saltar y cavar madrigueras"),
                new Animal("Murciélago enano", "Pipistrellus pipistrellus", "Ciudades y bosques", "Carnívoro", "Preocupación menor", "Pequeño murciélago que caza insectos al vuelo con ecolocalización"),
                new Animal("Zorro volador grande", "Pteropus vampyrus", "Selvas asiáticas", "Herbívoro", "Casi amenazado", "Murciélago frugívoro gigante de hasta dos metros de envergadura"),
                new Animal("Conejo europeo", "Oryctolagus cuniculus", "Matorrales", "Herbívoro", "En peligro", "Lagomorfo base de la dieta de linces y águilas ibéricas"),
                new Animal("Liebre ibérica", "Lepus granatensis", "Campos y matorrales", "Herbívoro", "Preocupación menor", "Lagomorfo veloz de largas orejas y patas traseras"),
                new Animal("Ratón de campo", "Apodemus sylvaticus", "Campos y bosques", "Omnívoro", "Preocupación menor", "Pequeño roedor nocturno de ojos grandes y cola larga"),
                new Animal("Elefante africano", "Loxodonta africana", "Sabanas", "Herbívoro", "En peligro", "El animal terrestre más grande, de trompa y colmillos de marfil"),
                new Animal("Damán roquero", "Procavia capensis", "Zonas rocosas africanas", "Herbívoro", "Preocupación menor", "Pequeño mamífero pariente lejano de los elefantes"),
                new Animal("Caballo de Przewalski", "Equus ferus przewalskii", "Estepas mongolas", "Herbívoro", "En peligro", "Último caballo verdaderamente salvaje, reintroducido en Mongolia"),
                new Animal("Rinoceronte blanco", "Ceratotherium simum", "Sabanas", "Herbívoro", "Casi amenazado", "Gran herbívoro de dos cuernos y labio ancho para pastar"),
                new Animal("Tapir amazónico", "Tapirus terrestris", "Selvas y humedales", "Herbívoro", "Vulnerable", "Ungulado de trompa corta y excelente nadador"),
                new Animal("Jabalí", "Sus scrofa", "Bosques", "Omnívoro", "Preocupación menor", "Cerdo salvaje de colmillos curvos y gran olfato"),
                new Animal("Pecarí de collar", "Dicotyles tajacu", "Desiertos y bosques americanos", "Omnívoro", "Preocupación menor", "Ungulado americano parecido al jabalí con collar claro"),
                new Animal("Hipopótamo común", "Hippopotamus amphibius", "Ríos y lagos africanos", "Herbívoro", "Vulnerable", "Gigante semiacuático territorial de enorme boca"),
                new Animal("Bisonte europeo", "Bison bonasus", "Bosques", "Herbívoro", "Casi amenazado", "El bóvido salvaje más grande de Europa, salvado de la extinción"),
                new Animal("Cabra montés", "Capra pyrenaica", "Montañas ibéricas", "Herbívoro", "Preocupación menor", "Bóvido trepador de grandes cuernos curvos"),
                new Animal("Muflón europeo", "Ovis gmelini musimon", "Montañas mediterráneas", "Herbívoro", "Preocupación menor", "Oveja no doméstica de cuernos enrollados en los machos"),
                new Animal("Ciervo rojo", "Cervus elaphus", "Bosques y montañas", "Herbívoro", "Preocupación menor", "Cérvido de gran cornamenta y berrea otoñal"),
                new Animal("Gacela común", "Gazella gazella", "Semidesiertos", "Herbívoro", "En peligro", "Antílope ágil de dorso oscuro y vientre blanco"),
                new Animal("Berrendo", "Antilocapra americana", "Praderas norteamericanas", "Herbívoro", "Preocupación menor", "El mamífero más rápido de América, de cuernos bifurcados"),
                new Animal("Jirafa", "Giraffa camelopardalis", "Sabanas", "Herbívoro", "Vulnerable", "El animal más alto, de cuello largo y manchas características"),
                new Animal("León", "Panthera leo", "Sabanas", "Carnívoro", "Vulnerable", "Gran felino social que vive en manadas lideradas por hembras"),
                new Animal("Leopardo", "Panthera pardus", "Selvas y sabanas", "Carnívoro", "Vulnerable", "Felino solitario de rosetas que sube presas a los árboles"),
                new Animal("Guepardo", "Acinonyx jubatus", "Sabanas abiertas", "Carnívoro", "Vulnerable", "El mamífero terrestre más rápido en carrera corta"),
                new Animal("Gato montés europeo", "Felis silvestris", "Bosques", "Carnívoro", "Preocupación menor", "Pequeño felino de cola gruesa, antepasado del gato doméstico"),
                new Animal("Oso pardo", "Ursus arctos", "Bosques y montañas", "Omnívoro", "Preocupación menor", "Gran plantígrado que hiberna en invierno"),
                new Animal("Panda gigante", "Ailuropoda melanoleuca", "Bosques de bambú", "Herbívoro", "Vulnerable", "Oso blanco y negro especializado en comer bambú"),
                new Animal("Mapache", "Procyon lotor", "Bosques y ciudades", "Omnívoro", "Preocupación menor", "Mamífero nocturno de antifaz negro y manos hábiles"),
                new Animal("Perro salvaje africano", "Lycaon pictus", "Sabanas", "Carnívoro", "En peligro", "Cánido de pelaje moteado que caza en manadas cooperativas"),
                new Animal("Zorro ártico", "Vulpes lagopus", "Tundra ártica", "Omnívoro", "Preocupación menor", "Cánido de pelaje blanco invernal adaptado al frío extremo"),
                new Animal("Comadreja", "Mustela nivalis", "Campos y setos", "Carnívoro", "Preocupación menor", "El mustélido más pequeño, ágil cazador de roedores"),
                new Animal("Tejón europeo", "Meles meles", "Bosques", "Omnívoro", "Preocupación menor", "Mustélido de rayas blancas y negras que vive en tejoneras"),
                new Animal("Nutria europea", "Lutra lutra", "Ríos", "Carnívoro", "Casi amenazado", "Mustélido acuático de pelaje denso y gran nadador"),
                new Animal("Ballena azul", "Balaenoptera musculus", "Océanos", "Carnívoro", "En peligro", "El animal más grande que ha existido, filtrador de kril"),
                new Animal("Delfín mular", "Tursiops truncatus", "Costas y mar abierto", "Carnívoro", "Preocupación menor", "Cetáceo inteligente de hocico corto y comportamiento social"),
                new Animal("Foca común", "Phoca vitulina", "Costas templadas", "Carnívoro", "Preocupación menor", "Pinnípedo moteado que descansa en bancos de arena"),
                new Animal("León marino de California", "Zalophus californianus", "Costas del Pacífico", "Carnívoro", "Preocupación menor", "Otárido de pabellones visibles y gran agilidad en tierra"),
                new Animal("Morsa", "Odobenus rosmarus", "Ártico", "Carnívoro", "Vulnerable", "Pinnípedo de largos colmillos que bucea en busca de moluscos"),
                new Animal("Dromedario", "Camelus dromedarius", "Desiertos", "Herbívoro", "No evaluado", "Camello de una joroba adaptado al calor y la sed"),
                new Animal("Llama", "Lama glama", "Andes", "Herbívoro", "No evaluado", "Camélido andino usado como animal de carga"),
                new Animal("Alpaca", "Vicugna pacos", "Andes", "Herbívoro", "No evaluado", "Camélido andino de fibra fina y suave"),
                new Animal("Mono aullador rojo", "Alouatta seniculus", "Selvas sudamericanas", "Herbívoro", "Preocupación menor", "Mono del Nuevo Mundo de aullidos que se oyen a kilómetros"),
                new Animal("Macaco de Berbería", "Macaca sylvanus", "Bosques norteafricanos", "Omnívoro", "En peligro", "El único mono salvaje de Europa, en Gibraltar"),
                new Animal("Babuino sagrado", "Papio hamadryas", "Sabanas y roquedos", "Omnívoro", "Preocupación menor", "Primates de melena plateada que viven en harenes"),
                new Animal("Chimpancé común", "Pan troglodytes", "Selvas africanas", "Omnívoro", "En peligro", "Simio inteligente que usa herramientas y vive en comunidades")
        ));
    }
}
