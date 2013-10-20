require 'rmagick'

def slug_name(directory)
    directory.gsub(/[ ]/, "_").downcase
end

def prerender(dirname, filename)
    scales = [1]
    sizes = [768]
    fullname = File.join(dirname, filename)
    ext = File.extname(fullname)
    slug = slug_name dirname
    path = File.dirname(fullname)
    basename = File.basename(filename, ext).downcase
    
    Dir.mkdir "trees/#{slug}" unless Dir.exist? "trees/#{slug}"

    _t = "_thumb_"
    sizes.each do|size|
        scales.each do|scale|
            width = (size * scale).to_i
            thumbname = "trees/#{slug}/#{basename}#{ext.downcase}"
            if(File.exist?(thumbname) == false)
                original = fullname
                image = Magick::ImageList.new(original)
                thumb = image.resize_to_fit(width)
                thumb.write(thumbname)
                thumb = nil
           end
        end
    end
end

def prerender_fixed(dirname, filename)
    scales = [0.75, 1, 1.5, 2]
    widths = [320]
    heights = [175]
    fullname = File.join(dirname, filename)
    ext = File.extname(fullname)
    path = File.dirname(fullname)
    basename = File.basename(filename, ext)

    _t = "_thumb_"
    widths.each_index do|index|
    	sizew = widths[index]
    	sizeh = heights[index]
        scales.each do|scale|
            width = (sizew * scale).to_i
            height = (sizeh * scale).to_i
            thumbname = "#{path}/#{basename}_thumb_#{width}w#{ext}"
			if(File.exist?(thumbname) == false)
                original = fullname
                image = Magick::ImageList.new(original)
                thumb = image.resize_to_fill(width, height, Magick::NorthGravity)
                thumb.write(thumbname)
           end
        end
    end
end


Dir.chdir "../../camp_joy_images"
tree_dirs = Dir.entries(".")

tree_dirs.each do|d|
    if (d.start_with?(".") == false and File.directory?(d) and d != "trees") then
        puts "Folder #{d}"
        images = Dir.entries d
        images.each do|i|
            unless(i.start_with?(".") or i.index("_thumb_") != nil or i.end_with?(".psd") or i.end_with?(".pptx")) then
                puts "Prerendering #{i}"
                prerender d, i
                #prerender_fixed d, i
            end
        end
    end
end

exit


