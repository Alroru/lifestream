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
                new Animal("Raya común", "Raja clavata", "Fondos arenosos costeros", "Carnívoro", "Casi amenazado", "Pez de cuerpo aplanado con aguijones en el lomo", "/images/animals/raya-comun.jpg"),
                new Animal("Tiburón blanco", "Carcharodon carcharias", "Océanos templados y tropicales", "Carnívoro", "Vulnerable", "Gran depredador marino de dientes triangulares aserrados", "/images/animals/tiburon-blanco.jpg"),
                new Animal("Rape común", "Lophius piscatorius", "Fondos marinos", "Carnívoro", "Preocupación menor", "Pez del fondo marino con señuelo luminoso para atraer presas", "/images/animals/rape-comun.jpg"),
                new Animal("Pez payaso", "Amphiprion ocellaris", "Arrecifes de coral", "Omnívoro", "Preocupación menor", "Pez naranja de arrecife que vive entre anémonas", "/images/animals/pez-payaso.jpg"),
                new Animal("Trucha común", "Salmo trutta", "Ríos y lagos fríos", "Carnívoro", "Preocupación menor", "Pez de agua dulce moteado muy apreciado en pesca", "/images/animals/trucha-comun.jpg"),
                new Animal("Barracuda", "Sphyraena barracuda", "Aguas tropicales costeras", "Carnívoro", "Preocupación menor", "Pez cazador alargado de mandíbulas potentes y veloz", "/images/animals/barracuda.jpg"),
                // Anfibios y reptiles
                new Animal("Tritón crestado", "Triturus cristatus", "Charcas y bosques húmedos", "Carnívoro", "Preocupación menor", "Anfibio de cresta dorsal dentada en época de cría", "/images/animals/triton-crestado.jpg"),
                new Animal("Salamandra común", "Salamandra salamandra", "Bosques húmedos", "Carnívoro", "Preocupación menor", "Anfibio negro con manchas amarillas y piel tóxica", "/images/animals/salamandra-comun.jpg"),
                new Animal("Rana común", "Pelophylax perezi", "Charcas y ríos", "Carnívoro", "Preocupación menor", "Anfibio verde de gran salto ligado al agua", "/images/animals/rana-comun.jpg"),
                new Animal("Sapo común", "Bufo bufo", "Jardines y bosques", "Carnívoro", "Preocupación menor", "Anfibio robusto de piel verrugosa y hábitos nocturnos", "/images/animals/sapo-comun.jpg"),
                new Animal("Tortuga mediterránea", "Testudo hermanni", "Matorral mediterráneo", "Herbívoro", "Casi amenazado", "Tortuga terrestre de caparazón abombado y espolón en la cola", "/images/animals/tortuga-mediterranea.jpg"),
                new Animal("Galápago europeo", "Emys orbicularis", "Humedales y ríos lentos", "Omnívoro", "Casi amenazado", "Tortuga acuática de caparazón oscuro con motas amarillas", "/images/animals/galapago-europeo.jpg"),
                new Animal("Pitón reticulada", "Malayopython reticulatus", "Selvas del sudeste asiático", "Carnívoro", "Preocupación menor", "Una de las serpientes más largas del mundo, constrictora", "/images/animals/piton-reticulada.jpg"),
                new Animal("Boa constrictora", "Boa constrictor", "Selvas americanas", "Carnívoro", "Preocupación menor", "Serpiente que asfixia a sus presas enrollándose en torno a ellas", "/images/animals/boa-constrictora.jpg"),
                new Animal("Víbora hocicuda", "Vipera latastei", "Matorrales ibéricos", "Carnívoro", "Vulnerable", "Serpiente venenosa ibérica de hocico respingón", "/images/animals/vibora-hocicuda.jpg"),
                new Animal("Cocodrilo del Nilo", "Crocodylus niloticus", "Ríos y lagos africanos", "Carnívoro", "Preocupación menor", "Gran reptil semiacuático de mandíbulas muy potentes", "/images/animals/cocodrilo-del-nilo.jpg"),
                new Animal("Caimán de anteojos", "Caiman crocodilus", "Humedales americanos", "Carnívoro", "Preocupación menor", "Cocodriliano de tamaño medio con cresta ósea entre los ojos", "/images/animals/caiman-de-anteojos.jpg"),
                // Aves
                new Animal("Avestruz", "Struthio camelus", "Sabanas africanas", "Omnívoro", "Preocupación menor", "Ave que no vuela, la más grande y rápida corriendo", "/images/animals/avestruz.jpg"),
                new Animal("Ánade azulón", "Anas platyrhynchos", "Lagos y ríos", "Omnívoro", "Preocupación menor", "Pato acuático de cabeza verde iridiscente en los machos", "/images/animals/anade-azulon.jpg"),
                new Animal("Flamenco común", "Phoenicopterus roseus", "Humedales salinos", "Omnívoro", "Preocupación menor", "Ave de ribera rosada de patas y cuello larguísimos", "/images/animals/flamenco-comun.jpg"),
                new Animal("Águila real", "Aquila chrysaetos", "Montañas", "Carnívoro", "Preocupación menor", "Ave de presa de gran envergadura y vuelo majestuoso", "/images/animals/aguila-real.jpg"),
                new Animal("Loro gris", "Psittacus erithacus", "Selvas africanas", "Herbívoro", "En peligro", "Loro famoso por su capacidad para imitar sonidos", "/images/animals/loro-gris.jpg"),
                new Animal("Periquito común", "Melopsittacus undulatus", "Estepas australianas", "Herbívoro", "Preocupación menor", "Pequeño loro verde de cola larga y vuelo ondulante", "/images/animals/periquito-comun.jpg"),
                new Animal("Búho real", "Bubo bubo", "Roquedos y bosques", "Carnívoro", "Preocupación menor", "Ave nocturna de grandes penachos y mirada penetrante", "/images/animals/buho-real.jpg"),
                new Animal("Petirrojo", "Erithacus rubecula", "Jardines y bosques", "Omnívoro", "Preocupación menor", "Pájaro de jardín de pecho rojizo y canto melodioso", "/images/animals/petirrojo.jpg"),
                new Animal("Ruiseñor", "Luscinia megarhynchos", "Matorrales", "Omnívoro", "Preocupación menor", "Pájaro cantor de plumaje discreto y canto potente", "/images/animals/ruisenor.jpg"),
                // Mamíferos
                new Animal("Canguro rojo", "Osphranter rufus", "Praderas australianas", "Herbívoro", "Preocupación menor", "Marsupial de gran tamaño que se desplaza a saltos", "/images/animals/canguro-rojo.jpg"),
                new Animal("Koala", "Phascolarctos cinereus", "Bosques de eucaliptos", "Herbívoro", "Vulnerable", "Marsupial arborícola que se alimenta casi solo de eucalipto", "/images/animals/koala.jpg"),
                new Animal("Bandicut marrón", "Isoodon macrourus", "Matorrales australianos", "Omnívoro", "Preocupación menor", "Pequeño marsupial de hocico largo que hoza en el suelo", "/images/animals/bandicut-marron.jpg"),
                new Animal("Demonio de Tasmania", "Sarcophilus harrisii", "Bosques de Tasmania", "Carnívoro", "En peligro", "Marsupial carroñero de mandíbula potente y mal genio", "/images/animals/demonio-de-tasmania.jpg"),
                new Animal("Perezoso de tres dedos", "Bradypus variegatus", "Copas de selvas americanas", "Herbívoro", "Preocupación menor", "Mamífero arborícola de movimientos lentos y vida colgada", "/images/animals/perezoso-tres-dedos.jpg"),
                new Animal("Oso hormiguero gigante", "Myrmecophaga tridactyla", "Sabanas y selvas", "Carnívoro", "Vulnerable", "Desdentado de larga lengua que devora hormigas y termitas", "/images/animals/oso-hormiguero-gigante.jpg"),
                new Animal("Armadillo de nueve bandas", "Dasypus novemcinctus", "Praderas y bosques", "Omnívoro", "Preocupación menor", "Mamífero acorazado capaz de saltar y cavar madrigueras", "/images/animals/armadillo-nueve-bandas.jpg"),
                new Animal("Murciélago enano", "Pipistrellus pipistrellus", "Ciudades y bosques", "Carnívoro", "Preocupación menor", "Pequeño murciélago que caza insectos al vuelo con ecolocalización", "/images/animals/murcielago-enano.jpg"),
                new Animal("Zorro volador grande", "Pteropus vampyrus", "Selvas asiáticas", "Herbívoro", "Casi amenazado", "Murciélago frugívoro gigante de hasta dos metros de envergadura", "/images/animals/zorro-volador-grande.jpg"),
                new Animal("Conejo europeo", "Oryctolagus cuniculus", "Matorrales", "Herbívoro", "En peligro", "Lagomorfo base de la dieta de linces y águilas ibéricas", "/images/animals/conejo-europeo.jpg"),
                new Animal("Liebre ibérica", "Lepus granatensis", "Campos y matorrales", "Herbívoro", "Preocupación menor", "Lagomorfo veloz de largas orejas y patas traseras", "/images/animals/liebre-iberica.jpg"),
                new Animal("Ratón de campo", "Apodemus sylvaticus", "Campos y bosques", "Omnívoro", "Preocupación menor", "Pequeño roedor nocturno de ojos grandes y cola larga", "/images/animals/raton-de-campo.jpg"),
                new Animal("Elefante africano", "Loxodonta africana", "Sabanas", "Herbívoro", "En peligro", "El animal terrestre más grande, de trompa y colmillos de marfil", "/images/animals/elefante-africano.jpg"),
                new Animal("Damán roquero", "Procavia capensis", "Zonas rocosas africanas", "Herbívoro", "Preocupación menor", "Pequeño mamífero pariente lejano de los elefantes", "/images/animals/daman-roquero.jpg"),
                new Animal("Caballo de Przewalski", "Equus ferus przewalskii", "Estepas mongolas", "Herbívoro", "En peligro", "Último caballo verdaderamente salvaje, reintroducido en Mongolia", "/images/animals/caballo-przewalski.jpg"),
                new Animal("Rinoceronte blanco", "Ceratotherium simum", "Sabanas", "Herbívoro", "Casi amenazado", "Gran herbívoro de dos cuernos y labio ancho para pastar", "/images/animals/rinoceronte-blanco.jpg"),
                new Animal("Tapir amazónico", "Tapirus terrestris", "Selvas y humedales", "Herbívoro", "Vulnerable", "Ungulado de trompa corta y excelente nadador", "/images/animals/tapir-amazonico.jpg"),
                new Animal("Jabalí", "Sus scrofa", "Bosques", "Omnívoro", "Preocupación menor", "Cerdo salvaje de colmillos curvos y gran olfato", "/images/animals/jabali.jpg"),
                new Animal("Pecarí de collar", "Dicotyles tajacu", "Desiertos y bosques americanos", "Omnívoro", "Preocupación menor", "Ungulado americano parecido al jabalí con collar claro", "/images/animals/pecari-de-collar.jpg"),
                new Animal("Hipopótamo común", "Hippopotamus amphibius", "Ríos y lagos africanos", "Herbívoro", "Vulnerable", "Gigante semiacuático territorial de enorme boca", "/images/animals/hipopotamo-comun.jpg"),
                new Animal("Bisonte europeo", "Bison bonasus", "Bosques", "Herbívoro", "Casi amenazado", "El bóvido salvaje más grande de Europa, salvado de la extinción", "/images/animals/bisonte-europeo.jpg"),
                new Animal("Cabra montés", "Capra pyrenaica", "Montañas ibéricas", "Herbívoro", "Preocupación menor", "Bóvido trepador de grandes cuernos curvos", "/images/animals/cabra-montes.jpg"),
                new Animal("Muflón europeo", "Ovis gmelini musimon", "Montañas mediterráneas", "Herbívoro", "Preocupación menor", "Oveja no doméstica de cuernos enrollados en los machos", "/images/animals/muflon-europeo.jpg"),
                new Animal("Ciervo rojo", "Cervus elaphus", "Bosques y montañas", "Herbívoro", "Preocupación menor", "Cérvido de gran cornamenta y berrea otoñal", "/images/animals/ciervo-rojo.jpg"),
                new Animal("Gacela común", "Gazella gazella", "Semidesiertos", "Herbívoro", "En peligro", "Antílope ágil de dorso oscuro y vientre blanco", "/images/animals/gacela-comun.jpg"),
                new Animal("Berrendo", "Antilocapra americana", "Praderas norteamericanas", "Herbívoro", "Preocupación menor", "El mamífero más rápido de América, de cuernos bifurcados", "/images/animals/berrendo.jpg"),
                new Animal("Jirafa", "Giraffa camelopardalis", "Sabanas", "Herbívoro", "Vulnerable", "El animal más alto, de cuello largo y manchas características", "/images/animals/jirafa.jpg"),
                new Animal("León", "Panthera leo", "Sabanas", "Carnívoro", "Vulnerable", "Gran felino social que vive en manadas lideradas por hembras", "/images/animals/leon.jpg"),
                new Animal("Leopardo", "Panthera pardus", "Selvas y sabanas", "Carnívoro", "Vulnerable", "Felino solitario de rosetas que sube presas a los árboles", "/images/animals/leopardo.jpg"),
                new Animal("Guepardo", "Acinonyx jubatus", "Sabanas abiertas", "Carnívoro", "Vulnerable", "El mamífero terrestre más rápido en carrera corta", "/images/animals/guepardo.jpg"),
                new Animal("Gato montés europeo", "Felis silvestris", "Bosques", "Carnívoro", "Preocupación menor", "Pequeño felino de cola gruesa, antepasado del gato doméstico", "/images/animals/gato-montes-europeo.jpg"),
                new Animal("Lince ibérico", "Lynx pardinus", "Matorral mediterráneo", "Carnívoro", "En peligro", "Felino moteado de orejas con pinceles, especialista en conejo", "/images/animals/lince-iberico.jpg"),
                new Animal("Oso pardo", "Ursus arctos", "Bosques y montañas", "Omnívoro", "Preocupación menor", "Gran plantígrado que hiberna en invierno", "/images/animals/oso-pardo.jpg"),
                new Animal("Panda gigante", "Ailuropoda melanoleuca", "Bosques de bambú", "Herbívoro", "Vulnerable", "Oso blanco y negro especializado en comer bambú", "/images/animals/panda-gigante.jpg"),
                new Animal("Mapache", "Procyon lotor", "Bosques y ciudades", "Omnívoro", "Preocupación menor", "Mamífero nocturno de antifaz negro y manos hábiles", "/images/animals/mapache.jpg"),
                new Animal("Perro salvaje africano", "Lycaon pictus", "Sabanas", "Carnívoro", "En peligro", "Cánido de pelaje moteado que caza en manadas cooperativas", "/images/animals/perro-salvaje-africano.jpg"),
                new Animal("Lobo ibérico", "Canis lupus signatus", "Bosques y montañas", "Carnívoro", "Casi amenazado", "Subespecie de lobo de la península ibérica que vive en manadas", "/images/animals/lobo-iberico.jpg"),
                new Animal("Zorro ártico", "Vulpes lagopus", "Tundra ártica", "Omnívoro", "Preocupación menor", "Cánido de pelaje blanco invernal adaptado al frío extremo", "/images/animals/zorro-artico.jpg"),
                new Animal("Zorro rojo", "Vulpes vulpes", "Bosques y praderas", "Omnívoro", "Preocupación menor", "Cánido pequeño y adaptable de pelaje rojizo y cola frondosa", "/images/animals/zorro-rojo.jpg"),
                new Animal("Comadreja", "Mustela nivalis", "Campos y setos", "Carnívoro", "Preocupación menor", "El mustélido más pequeño, ágil cazador de roedores", "/images/animals/comadreja.jpg"),
                new Animal("Tejón europeo", "Meles meles", "Bosques", "Omnívoro", "Preocupación menor", "Mustélido de rayas blancas y negras que vive en tejoneras", "/images/animals/tejon-europeo.jpg"),
                new Animal("Nutria europea", "Lutra lutra", "Ríos", "Carnívoro", "Casi amenazado", "Mustélido acuático de pelaje denso y gran nadador", "/images/animals/nutria-europea.jpg"),
                new Animal("Ballena azul", "Balaenoptera musculus", "Océanos", "Carnívoro", "En peligro", "El animal más grande que ha existido, filtrador de kril", "/images/animals/ballena-azul.jpg"),
                new Animal("Delfín mular", "Tursiops truncatus", "Costas y mar abierto", "Carnívoro", "Preocupación menor", "Cetáceo inteligente de hocico corto y comportamiento social", "/images/animals/delfin-mular.jpg"),
                new Animal("Foca común", "Phoca vitulina", "Costas templadas", "Carnívoro", "Preocupación menor", "Pinnípedo moteado que descansa en bancos de arena", "/images/animals/foca-comun.jpg"),
                new Animal("León marino de California", "Zalophus californianus", "Costas del Pacífico", "Carnívoro", "Preocupación menor", "Otárido de pabellones visibles y gran agilidad en tierra", "/images/animals/leon-marino-california.jpg"),
                new Animal("Morsa", "Odobenus rosmarus", "Ártico", "Carnívoro", "Vulnerable", "Pinnípedo de largos colmillos que bucea en busca de moluscos", "/images/animals/morsa.jpg"),
                new Animal("Dromedario", "Camelus dromedarius", "Desiertos", "Herbívoro", "No evaluado", "Camello de una joroba adaptado al calor y la sed", "/images/animals/dromedario.jpg"),
                new Animal("Llama", "Lama glama", "Andes", "Herbívoro", "No evaluado", "Camélido andino usado como animal de carga", "/images/animals/llama.jpg"),
                new Animal("Alpaca", "Vicugna pacos", "Andes", "Herbívoro", "No evaluado", "Camélido andino de fibra fina y suave", "/images/animals/alpaca.jpg"),
                new Animal("Mono aullador rojo", "Alouatta seniculus", "Selvas sudamericanas", "Herbívoro", "Preocupación menor", "Mono del Nuevo Mundo de aullidos que se oyen a kilómetros", "/images/animals/mono-aullador-rojo.jpg"),
                new Animal("Macaco de Berbería", "Macaca sylvanus", "Bosques norteafricanos", "Omnívoro", "En peligro", "El único mono salvaje de Europa, en Gibraltar", "/images/animals/macaco-de-berberia.jpg"),
                new Animal("Babuino sagrado", "Papio hamadryas", "Sabanas y roquedos", "Omnívoro", "Preocupación menor", "Primates de melena plateada que viven en harenes", "/images/animals/babuino-sagrado.jpg"),
                new Animal("Chimpancé común", "Pan troglodytes", "Selvas africanas", "Omnívoro", "En peligro", "Simio inteligente que usa herramientas y vive en comunidades", "/images/animals/chimpance-comun.jpg")
        ));
    }
}
